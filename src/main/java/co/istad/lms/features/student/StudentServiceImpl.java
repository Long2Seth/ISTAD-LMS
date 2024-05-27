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



    private void validateUserDoesNotExist(StudentRequest studentRequest) {
        String email = studentRequest.userRequest().email();
        String phoneNumber = studentRequest.userRequest().phoneNumber();
        String alias = studentRequest.userRequest().alias();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with email = %s already exists", email));
        }


        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with phone number = %s already exists", phoneNumber));
        }

        if (userRepository.existsByAlias(alias)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with alias = %s already exists", alias));
        }
    }






    private User setUpNewUser(StudentRequest studentRequest) {
        var userReq = studentRequest.userRequest();
        User user = new User();

        user.setAlias(userReq.alias());
        user.setEmail(userReq.email());
        user.setPhoneNumber(userReq.phoneNumber());
        user.setPassword(passwordEncoder.encode(userReq.password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(userReq.gender());
        user.setNameEn(userReq.nameEn());
        user.setNameKh(userReq.nameKh());
        user.setUsername(userReq.username());
        user.setProfileImage(userReq.profileImage());
        user.setCityOrProvince(userReq.cityOrProvince());
        user.setKhanOrDistrict(userReq.khanOrDistrict());
        user.setSangkatOrCommune(userReq.sangkatOrCommune());
        user.setStreet(userReq.street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setBirthPlace(toBirthPlace(userReq.birthPlace()));

        Authority userReadAuthority = authorityRepository.findByAuthorityName("user:read")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Authority with name = user:read not found"));

        Authority userWriteAuthority = authorityRepository.findByAuthorityName("user:write")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Authority with name = user:write not found"));
        // Assign authorities to the user
        user.setAuthorities(List.of(userReadAuthority, userWriteAuthority));

        return user;
    }



    private void updateUserFromRequest(User user, StudentRequest studentRequest) {
        var userReq = studentRequest.userRequest();

        user.setEmail(userReq.email());
        user.setPhoneNumber(userReq.phoneNumber());
        user.setGender(userReq.gender());
        user.setNameEn(userReq.nameEn());
        user.setNameKh(userReq.nameKh());
        user.setUsername(userReq.username());
        user.setProfileImage(userReq.profileImage());
        user.setCityOrProvince(userReq.cityOrProvince());
        user.setKhanOrDistrict(userReq.khanOrDistrict());
        user.setSangkatOrCommune(userReq.sangkatOrCommune());
        user.setStreet(userReq.street());
        user.setBirthPlace(toBirthPlace(userReq.birthPlace()));
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



    private StudentResponse updateStudentStatus(String uuid, boolean status) {
        return updateStudentAttribute(uuid, status);
    }





    private StudentResponse updateStudentAttribute(String uuid, boolean status) {
        int updatedRows = studentRepository.updateStatusByUuid(uuid, status);
        if (updatedRows > 0) {
            return studentMapper.toResponse(findStudentByUuid(uuid));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s not found", uuid));
        }
    }





    @Override
    public Page<StudentResponse> getStudents(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Student> students = studentRepository.findAll(pageRequest);
        List<Student> filteredStudents = students.stream()
                .filter(student -> !student.isDeleted() || !student.isStatus())
                .toList();
        return new PageImpl<>(filteredStudents, pageRequest, filteredStudents.size())
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        validateUserDoesNotExist(studentRequest);
        Student student = studentMapper.toRequest(studentRequest);

        student.setUuid(UUID.randomUUID().toString());
        student.setStatus(false);
        student.setDeleted(false);

        User user = setUpNewUser(studentRequest);
        student.setUser(userRepository.save(user));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public StudentResponse updateStudentByUuid(String uuid, StudentRequest studentRequest) {
        Student student = findStudentByUuid(uuid);
        validateUserDoesNotExist(studentRequest);

        User user = student.getUser();
        updateUserFromRequest(user, studentRequest);
        student.setUser(userRepository.save(user));

        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudentByUuid(String uuid) {
        Student student = findStudentByUuid(uuid);
        userRepository.delete(student.getUser());
        studentRepository.delete(student);
    }

    @Override
    public StudentResponse getStudentByUuid(String uuid) {
        return studentMapper.toResponse(findStudentByUuid(uuid));
    }

    @Override
    public StudentResponse disableStudentByUuid(String uuid) {
        return updateStudentStatus(uuid, false);
    }

    @Override
    public StudentResponse enableStudentByUuid(String uuid) {
        return updateStudentStatus(uuid, true);
    }

    @Override
    public StudentResponse blockStudentByUuid(String uuid) {

        int updatedRows = studentRepository.updateDeletedByUuid(uuid, true);
        if (updatedRows > 0) {
            return studentMapper.toResponse(findStudentByUuid(uuid));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Student with uuid = %s not found", uuid));
        }

    }


}
