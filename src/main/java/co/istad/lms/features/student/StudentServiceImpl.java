package co.istad.lms.features.student;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.authority.AuthorityRepository;
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


    private List<Authority> getDefaultAuthorities() {
        Authority userReadAuthority = authorityRepository.findByAuthorityName("user:read")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority with name = user:read not found"));
        Authority userWriteAuthority = authorityRepository.findByAuthorityName("user:write")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Authority with name = user:write not found"));

        return List.of(userReadAuthority, userWriteAuthority);
    }


    private User setUpNewUser(StudentRequest studentRequest) {
        var userReq = studentRequest.user();
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



    private Student findStudentByUuid(String uuid) {
        return studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s not found", uuid)));
    }




    @Override
    public Page<StudentResponse> getStudents(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Student> students = studentRepository.findAll(pageRequest);
        List<Student> filteredStudents = students.stream()
                .filter(student -> !student.isDeleted())
                .filter(student -> !student.isStatus())
                .toList();
        return new PageImpl<>(filteredStudents, pageRequest, filteredStudents.size())
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        if (userRepository.existsByEmailOrUsername(studentRequest.user().email() , studentRequest.user().username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentRequest.user().email()));
        }


        if (userRepository.existsByUsername(studentRequest.user().username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username = %s already exists", studentRequest.user().username()));
        }

        // Map student request to student
        Student student = studentMapper.toRequest(studentRequest);
        student.setUuid(UUID.randomUUID().toString());
        student.setStatus(false);
        student.setDeleted(false);

        // Map user request to user
        User user = setUpNewUser(studentRequest);
//        user.setBirthPlace(toBirthPlace(studentRequest.user().birthPlace()));
        user.setAuthorities(getDefaultAuthorities());
        // Save user
        student.setUser(userRepository.save(user));

        return studentMapper.toResponse(studentRepository.save(student));
    }




    @Override
    public StudentResponse updateStudentByUuid(String uuid, StudentRequest studentRequest) {

        if (userRepository.existsByEmailOrUsername(studentRequest.user().email() , studentRequest.user().username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", studentRequest.user().email()));
        }


        if (userRepository.existsByUsername(studentRequest.user().username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username = %s already exists", studentRequest.user().username()));
        }

        Student student = studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s not found", uuid)));

        User user = userRepository.findById(student.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id = %s not found", student.getUser().getId())));

        // Update user

        user.setNameEn(studentRequest.user().nameEn());
        user.setNameKh(studentRequest.user().nameKh());
        user.setUsername(studentRequest.user().username());
        user.setGender(studentRequest.user().gender());
        user.setEmail(studentRequest.user().email());
        user.setProfileImage(studentRequest.user().profileImage());
        user.setPhoneNumber(studentRequest.user().phoneNumber());
//        user.setCityOrProvince(studentRequest.user().cityOrProvince());
//        user.setKhanOrDistrict(studentRequest.user().khanOrDistrict());
//        user.setSangkatOrCommune(studentRequest.user().sangkatOrCommune());
//        user.setStreet(studentRequest.user().street());
//        user.setPassword(passwordEncoder.encode(studentRequest.user().password()));
//        user.setBirthPlace(toBirthPlace(studentRequest.user().birthPlace()));


        // Save user
        student.setUser(userRepository.save(user));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudentByUuid(String uuid) {

        // find student by uuid
        Student student = findStudentByUuid(uuid);
        // delete student that found by uuid
        studentRepository.delete(student);

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




}
