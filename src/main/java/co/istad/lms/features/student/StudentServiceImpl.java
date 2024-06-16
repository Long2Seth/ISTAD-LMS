package co.istad.lms.features.student;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.file.FileMetaDataRepository;
import co.istad.lms.features.student.dto.*;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.UserService;
import co.istad.lms.mapper.StudentMapper;
import co.istad.lms.mapper.UserMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final FileMetaDataRepository fileMetaDataRepository;


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
        user.setIsDeleted(false);
        user.setStatus(false);
        user.setRawPassword(userService.generateStrongPassword(10));
        user.setPassword(passwordEncoder.encode(user.getRawPassword()));
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

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with username %s not found", email)
                ));

        // Log the existing user details
        log.info("Existing User: {}", user);
        log.info("Existing Student: {}", user.getStudent());

        if(userRepository.existsByEmail(studentSettingRequest.email()) && !user.getEmail().equals(studentSettingRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentSettingRequest.email()));
        }

        // Update user from student request
        studentMapper.updateStudentSettingRequest(user.getStudent(), studentSettingRequest);

        // Log the updated user details
        log.info("Updated Student: {}", user.getStudent());

        // Save student
        studentRepository.save(user.getStudent());

        // Save user
        userRepository.save(user);

        // Log final saved user details
        log.info("Saved User: {}", user);
        log.info("Saved Student: {}", user.getStudent());
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
