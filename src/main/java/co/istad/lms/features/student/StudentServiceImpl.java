package co.istad.lms.features.student;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.student.dto.*;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.StudentMapper;
import co.istad.lms.mapper.UserMapper;
import co.istad.lms.utils.DefaultAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final DefaultAuthority defaultAuthority;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;



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

        // Check if user exists by email or username that find in userRepository if not throw exception
        if (userRepository.existsByEmailOrUsername(studentRequest.email(), studentRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentRequest.email()));
        }


        // Map user request to user
        User user = userMapper.fromStudentRequest(studentRequest);

        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(studentRequest.password()));
        user.setIsDeleted(false);
        user.setStatus(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities(defaultAuthority.getDefaultAuthoritiesStudent());

        //Save user
        userRepository.save(user);

        // Map student request to student
        Student student = studentMapper.toRequest(studentRequest);
        student.setUuid(UUID.randomUUID().toString());

        // Save user
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

        // Check if user exists by email or username that find in userRepository if not throw exception
        if (userRepository.existsByEmailOrUsernameAndUuidNot(studentRequest.email(), studentRequest.username(), uuid)) {
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
