package co.istad.lms.features.student;

import co.istad.lms.domain.*;
import co.istad.lms.domain.Class;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.classes.ClassRepository;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.file.FileMetaDataRepository;
import co.istad.lms.features.student.dto.*;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.UserService;
import co.istad.lms.features.yearofstudy.YearOfStudyRepository;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyStudentAchievementResponse;
import co.istad.lms.mapper.StudentMapper;
import co.istad.lms.mapper.UserMapper;
import co.istad.lms.util.DateTimeUtil;
import co.istad.lms.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final FileMetaDataRepository fileMetaDataRepository;
    private final ClassRepository classRepository;
    private final YearOfStudyRepository yearOfStudyRepository;
    private final StudyProgramRepository studyProgramRepository;


    @Override
    public Set<Authority> getDefaultAuthoritiesStudent() {
        // Set default authorities
        Set<Authority> authorities = new HashSet<>();
        authorities.addAll(authorityRepository.findAllByAuthorityName("course:read"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("user:read"));

        return authorities;

    }


    @Override
    public Page<StudentResponse> getStudents(int page, int limit) {
        // Create page request with sort by id
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        // Find all students that in studentRepository
        Page<Student> students = studentRepository.findAll(pageRequest);
        // Filter students that is not deleted and not status
        List<Student> filteredStudents = students.stream()
                .filter(student -> !student.getUser().getIsDeleted())
                .filter(student -> !student.getUser().getStatus())
                .toList();

        return new PageImpl<>(filteredStudents, pageRequest, filteredStudents.size())
                .map(studentMapper::toResponse);


    }


    @Override
    public Page<StudentResponseDetail> getStudentsDetail(int page, int limit) {

        // Create page request with sort by id
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        // Find all students that in studentRepository
        Page<Student> students = studentRepository.findAll(pageRequest);

        // Filter students that is not deleted and not status
        List<Student> filteredStudents = students.stream()
                .filter(student -> !student.getUser().getIsDeleted())
                .filter(student -> !student.getUser().getStatus())
                .toList();

        // Return page of students
        return new PageImpl<>(filteredStudents, pageRequest, filteredStudents.size())
                .map(studentMapper::toResponseDetail);

    }


    @Override
    public void createStudent(StudentRequest studentRequest) {
        // Check if the email already exists from the database
        if (userRepository.existsByEmail(studentRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", studentRequest.email())
            );
        }

        if (studentRequest.profileImage() != null && !studentRequest.profileImage().trim().isEmpty() && !fileMetaDataRepository.existsByFileName(studentRequest.profileImage())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("File with name = %s not found!", studentRequest.profileImage()));
        }


        // Map user request to user
        User user = userMapper.fromStudentRequest(studentRequest);

        user.setUuid(UUID.randomUUID().toString());

        // Generate password rawPassword to encrypt
        String rawPassword = userService.generateStrongPassword(10);
        try {
            //generate key for encrypt
            SecretKey key = OtpUtil.generateKey();

            //encrypt password
            String encryptedPassword = OtpUtil.encryptOTP(rawPassword, key);

            //set raw password with encrypt password
            user.setRawPassword(encryptedPassword);
            // Set password to null
            user.setPassword(null);

        } catch (Exception e) {
            throw new RuntimeException("Error generating or encrypting password", e);
        }

        // Set dob from string to LocalDate that comes from the request validation
        LocalDate dob = DateTimeUtil.stringToLocalDate(studentRequest.dob(), "dob");
        user.setDob(dob);

        user.setIsDeleted(false);
        user.setStatus(false);
        user.setUsername(studentRequest.nameEn().trim().replaceAll("\\s+", "-") + "-" + studentRequest.dob());
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities(getDefaultAuthoritiesStudent());

        // Save user
        userRepository.save(user);

        // Map student request to student
        Student student = studentMapper.toRequest(studentRequest);
        student.setUuid(UUID.randomUUID().toString());

        // Save user in student
        student.setUser(user);

        // Save student
        studentRepository.save(student);
    }


    @Override
    public StudentResponseDetail updateStudentByUuid(String uuid, StudentRequestUpdate studentRequest) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        if (studentRequest.profileImage() != null && !studentRequest.profileImage().trim().isEmpty() && !fileMetaDataRepository.existsByFileName(studentRequest.profileImage())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("File with name = %s not found!", studentRequest.profileImage()));
        }

        // Check if user exists by email or username that find in userRepository if not throw exception
        if (userRepository.existsByEmailOrUsernameAndUuidNot(studentRequest.email(), user.getUsername(), uuid)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentRequest.email()));
        }

        // Update user from student request
        userMapper.updateUserFromStudentRequest(user, studentRequest);

        // Save user
        userRepository.save(user);

        // Find student by user
        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        // Update student from student request
        student.setUser(user);

        // Save student
        Student savedStudent = studentRepository.save(student);

        // Return student response detail
        studentMapper.updateStudentFromRequest(savedStudent, studentRequest);

        return studentMapper.toResponseDetail(savedStudent);


    }


    @Override
    public void updateSettingStudent(StudentSettingRequest studentSettingRequest) {


        // Get authentication from security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if authentication is null or not authenticated
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        // Get principal from authentication
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        // Get email from UserDetails
        UserDetails userDetails = (UserDetails) principal;
        String email = userDetails.getUsername();

        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with username %s not found", email)
                ));

        userMapper.updateUserFromStudentSettingRequest(user, studentSettingRequest);


        // Save user
        userRepository.save(user);

        if (userRepository.existsByEmail(studentSettingRequest.email()) && !user.getEmail().equals(studentSettingRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentSettingRequest.email()));
        }

        // Update user from student request
        studentMapper.updateStudentSettingRequest(user.getStudent(), studentSettingRequest);

        // Save student
        studentRepository.save(user.getStudent());


    }


    @Override
    public StudentAchievementResponse  getStudentAchievement() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        UserDetails userDetails = (UserDetails) principal;
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with username %s not found", email)
                ));

        System.out.println("User: " + user);

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Student with username %s not found", email)
                ));

        System.out.println("Student: " + student);

        Set<Class> studentClasses = student.getClasses();

        Set<Course> course = student.getCourses();


        StudyProgram studyProgram = studyProgramRepository.findByClassesIn(studentClasses)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Study program not found"
                ));


        Set<YearOfStudy> yearOfStudies = new HashSet<>();
        for (Course c : course) {
            yearOfStudies.addAll(yearOfStudyRepository.findByCourses(c));
        }
        if (yearOfStudies.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Year of study not found");
        }


        Set<YearOfStudyStudentAchievementResponse> yearOfStudyResponses = yearOfStudies.stream()
                .map(yearOfStudy -> new YearOfStudyStudentAchievementResponse(
                        yearOfStudy.getYear(),
                        yearOfStudy.getSemester(),
                        yearOfStudy.getCourses().stream()
                                .map(courses -> new CourseResponse(courses.getUuid(), courses.getTitle(), courses.getCredit()))
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toSet());

        return new StudentAchievementResponse(
                user.getNameEn(),
                user.getNameKh(),
                user.getDob(),
                studyProgram.getDegree().getLevel(),
                studyProgram.getStudyProgramName(),
                student.getAvatar(),
                yearOfStudyResponses
        );
    }

    @Override
    public void deleteStudentByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        // delete student that found by uuid
        studentRepository.delete(student);

    }


    @Override
    public StudentResponseDetail getStudentDetailByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        return studentMapper.toResponseDetail(student);
    }


    @Override
    public StudentResponse getStudentByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        return studentMapper.toResponse(student);


    }


    @Override
    public void disableStudentByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        // Set status to true
        user.setStatus(true);

        // Save user
        studentRepository.save(student);


    }


    @Override
    public void enableStudentByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        // Set status to false
        user.setStatus(false);

        // Save user
        studentRepository.save(student);


    }


    @Override
    public void blockStudentByUuid(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("User with uuid = %s not found", uuid)
                        )
                );

        Student student = studentRepository.findByUser(user)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Student with uuid = %s not found", uuid)
                        )
                );

        // Set isDeleted to true
        user.setIsDeleted(true);

        // Save user
        studentRepository.save(student);


    }


}
