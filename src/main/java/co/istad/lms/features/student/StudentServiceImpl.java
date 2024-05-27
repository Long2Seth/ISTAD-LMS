package co.istad.lms.features.student;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.StudentMapper;
import co.istad.lms.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public Page<StudentResponse> getStudents(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Student> students = studentRepository.findAll(pageRequest);
        List<Student> filteredStudents = students.stream()
                .filter(student -> !student.isDeleted() && !student.isStatus())
                .toList();
        return new PageImpl<>(filteredStudents, pageRequest, filteredStudents.size())
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        // Validate if user already exists
        String email = studentRequest.userRequest().email();
        String phoneNumber = studentRequest.userRequest().phoneNumber();
        String alias = studentRequest.userRequest().alias();
        String username = studentRequest.userRequest().username();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", email));
        }

        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with phone number = %s already exists", phoneNumber));
        }

        if (userRepository.existsByAlias(alias)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with alias = %s already exists", alias));
        }

        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username = %s already exists", username));
        }
        // Map student request to student
        Student student = studentMapper.toRequest(studentRequest);
        student.setUuid(UUID.randomUUID().toString());
        student.setStatus(false);
        student.setDeleted(false);

        // Map user request to user
        User user = setUpNewUser(studentRequest);
        user.setAuthorities(getDefaultAuthorities());
        // Save user
        student.setUser(userRepository.save(user));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse updateStudentByUuid(String uuid, StudentRequest studentRequest) {

        if (userRepository.existsByEmail(studentRequest.userRequest().email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentRequest.userRequest().email()));
        }

        if (userRepository.existsByPhoneNumber(studentRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with phone number = %s already exists", studentRequest.userRequest().phoneNumber()));
        }

        if (userRepository.existsByUsername(studentRequest.userRequest().username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username = %s already exists", studentRequest.userRequest().username()));
        }

        Student student = studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s not found", uuid)));

        User user = userRepository.findById(student.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id = %s not found", student.getUser().getId())));

        // Update user
        user.setAlias(studentRequest.userRequest().alias());
        user.setNameEn(studentRequest.userRequest().nameEn());
        user.setNameKh(studentRequest.userRequest().nameKh());
        user.setUsername(studentRequest.userRequest().username());
        user.setGender(studentRequest.userRequest().gender());
        user.setEmail(studentRequest.userRequest().email());
        user.setProfileImage(studentRequest.userRequest().profileImage());
        user.setPhoneNumber(studentRequest.userRequest().phoneNumber());
        user.setCityOrProvince(studentRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(studentRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(studentRequest.userRequest().sangkatOrCommune());
        user.setStreet(studentRequest.userRequest().street());
        user.setPassword(passwordEncoder.encode(studentRequest.userRequest().password()));
        user.setBirthPlace(toBirthPlace(studentRequest.userRequest().birthPlace()));
        user.setAuthorities(getDefaultAuthorities());

        // Save user
        student.setUser(userRepository.save(user));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudentByUuid(String uuid) {

        // find student by uuid
        Student student = findStudentByUuid(uuid);

        // fine user by alias and assign to string alias
        String alias = student.getUser().getAlias();
        // delete student that found by uuid
        studentRepository.delete(student);
        // fine user by alias and assign to string alias
        User user  = userRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with alias = %s not found", alias)));
        // delete user that found by alias
        userRepository.delete(user);

    }

    @Override
    public StudentResponse getStudentByUuid(String uuid) {
        return studentMapper.toResponse(findStudentByUuid(uuid));
    }

    @Override
    public StudentResponse disableStudentByUuid(String uuid) {
        Student student = findStudentByUuid(uuid);
        student.setStatus(false);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse enableStudentByUuid(String uuid) {
        Student student = findStudentByUuid(uuid);
        student.setStatus(true);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse blockStudentByUuid(String uuid) {
        Student student = findStudentByUuid(uuid);
        student.setDeleted(true);
        return studentMapper.toResponse(studentRepository.save(student));
    }


    private User setUpNewUser(StudentRequest studentRequest) {
        var userReq = studentRequest.userRequest();
        User user = userMapper.fromUserRequest(userReq);

        user.setPassword(passwordEncoder.encode(userReq.password()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        return user;
    }


    private BirthPlace toBirthPlace(JsonBirthPlace birthPlaceRequest) {
        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(birthPlaceRequest.cityOrProvince());
        birthPlace.setKhanOrDistrict(birthPlaceRequest.khanOrDistrict());
        birthPlace.setSangkatOrCommune(birthPlaceRequest.sangkatOrCommune());
        birthPlace.setVillageOrPhum(birthPlaceRequest.villageOrPhum());
        birthPlace.setStreet(birthPlaceRequest.street());
        birthPlace.setHouseNumber(birthPlaceRequest.houseNumber());
        return birthPlace;
    }

    private List<Authority> getDefaultAuthorities() {
        Authority userReadAuthority = authorityRepository.findByAuthorityName("user:read")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority with name = user:read not found"));
        Authority userWriteAuthority = authorityRepository.findByAuthorityName("user:write")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority with name = user:write not found"));

        return List.of(userReadAuthority, userWriteAuthority);
    }

    private Student findStudentByUuid(String uuid) {
        return studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s not found", uuid)));
    }



}
