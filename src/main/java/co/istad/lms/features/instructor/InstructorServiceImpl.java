package co.istad.lms.features.instructor;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.InstructorMapper;
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
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;




    private List<Authority> getAuthorities(List<AuthorityRequestToUser> authorityRequests) {
        List<Authority> authorities = new ArrayList<>();
        for (AuthorityRequestToUser request : authorityRequests) {
            Authority authority = authorityRepository.findByAuthorityName(request.authorityName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Role with name = %s was not found.", request.authorityName())));
            authorities.add(authority);
        }
        return authorities;
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



    @Override
    public InstructorResponse createInstructor(InstructorRequest instructorRequest) {

        // validate if the user already exists
        // Check if the username or email already exists from database
        if (userRepository.existsByEmailOrUsername(instructorRequest.user().email() , instructorRequest.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s have already exists", instructorRequest.user().email() , instructorRequest.user().username())
            );
        }

        Instructor instructor = instructorMapper.toRequest(instructorRequest);
        instructor.setUuid(UUID.randomUUID().toString());
        instructor.setDeleted(false);
        instructor.setStatus(false);

        User user = userMapper.fromUserRequest(instructorRequest.user());
        user.setPassword(passwordEncoder.encode(instructorRequest.user().password()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        List<Authority> authorities = getAuthorities(instructorRequest.user().authorities());
        // Save the Authority entity before associating it with the User entity
        user.setAuthorities(authorities);

        userRepository.save(user);

        instructor.setUser(user);
        Instructor savedInstructor = instructorRepository.save(instructor);

        return instructorMapper.toResponse(savedInstructor);
    }


    @Override
    public InstructorResponse updateInstructorByUuid(String uuid, InstructorRequestDetail instructorRequestDetail) {

        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        User user = userRepository.findByUuid(instructorRequestDetail.user().uuid())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s not found", instructorRequestDetail.user().uuid())
                ));

        if (userRepository.existsByEmailOrUsername(instructorRequestDetail.user().username() , instructorRequestDetail.user().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", instructorRequestDetail.user().email())
            );
        }


        // Update instructor details
        instructor.setDegree(instructorRequestDetail.degree());
        instructor.setMajor(instructorRequestDetail.major());
        instructor.setHighSchool(instructorRequestDetail.highSchool());
        instructor.setStudyAtUniversityOrInstitution(instructorRequestDetail.studyAtUniversityOrInstitution());
        instructor.setExperienceAtWorkingPlace(instructorRequestDetail.experienceAtWorkingPlace());
        instructor.setExperienceYear(instructorRequestDetail.experienceYear());
        instructor.setDegreeGraduationDate(instructorRequestDetail.degreeGraduationDate());
        instructor.setHighSchoolGraduationDate(instructorRequestDetail.highSchoolGraduationDate());
        instructor.setDeleted(false);
        instructor.setStatus(false);

        // Update user details

        user.setEmail(instructorRequestDetail.user().email());
        user.setPhoneNumber(instructorRequestDetail.user().phoneNumber());
        user.setPassword(passwordEncoder.encode(instructorRequestDetail.user().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(instructorRequestDetail.user().gender());
        user.setNameEn(instructorRequestDetail.user().nameEn());
        user.setNameKh(instructorRequestDetail.user().nameKh());
        user.setUsername(instructorRequestDetail.user().username());
        user.setProfileImage(instructorRequestDetail.user().profileImage());
//        user.setCityOrProvince(instructorRequestDetail.user().cityOrProvince());
//        user.setKhanOrDistrict(instructorRequestDetail.user().khanOrDistrict());
//        user.setSangkatOrCommune(instructorRequestDetail.user().sangkatOrCommune());
//        user.setStreet(instructorRequestDetail.user().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Update birth place
//        user.setBirthPlace(toBirthPlace(instructorRequestDetail.user().birthPlace()));
        // Update authorities
        List<Authority> authorities = getAuthorities(instructorRequestDetail.user().authorities());
        // Save the Authority entity before associating it with the User entity
        user.setAuthorities(authorities);

        // Save the updated user and instructor
        userRepository.save(user);
        instructor.setUser(user);
        Instructor savedAdmin = instructorRepository.save(instructor);

        return instructorMapper.toResponse(savedAdmin);
    }

    @Override
    public InstructorResponse getInstructorByUuid(String uuid) {

        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        return instructorMapper.toResponse(instructor);
    }

    @Override
    public void deleteInstructorByUuid(String uuid) {
        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        instructorRepository.delete(instructor);

    }

    @Override
    public InstructorResponse disableInstructorByUuid(String uuid) {
        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));
        instructor.setStatus(false);
        return instructorMapper.toResponse(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse enableInstructorByUuid(String uuid) {

        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));
        instructor.setStatus(true);
        return instructorMapper.toResponse(instructorRepository.save(instructor));

    }

    @Override
    public InstructorResponse blockInstructorByUuid(String uuid) {

        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));
        instructor.setDeleted(true);
        return instructorMapper.toResponse(instructorRepository.save(instructor));
        
    }

    @Override
    public Page<InstructorResponse> getAllInstructor(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC,"id"));
        Page<Instructor> instructorsRequest = instructorRepository.findAll(pageRequest);
        List<Instructor> instructors = instructorsRequest.stream()
                .filter(instructor -> !instructor.isDeleted())
                .filter(instructor -> !instructor.isStatus())
                .toList();
        Page<Instructor> filteredInstructors = new PageImpl<>(instructors, pageRequest, instructors.size());

        return filteredInstructors.map(instructorMapper::toResponse);
    }
}
