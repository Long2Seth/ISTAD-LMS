package co.istad.lms.features.intructor;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.intructor.dto.InstructorRequest;
import co.istad.lms.features.intructor.dto.InstructorResponse;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.mapper.InstructorMapper;
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
    private final PasswordEncoder passwordEncoder;
    @Override
    public InstructorResponse createInstructor(InstructorRequest instructorRequest) {

        if (userRepository.existsByAlias(instructorRequest.userRequest().alias())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with alias = %s already exists", instructorRequest.userRequest().alias())
            );
        }

        if (userRepository.existsByEmail(instructorRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", instructorRequest.userRequest().email())
            );
        }

        if (userRepository.existsByPhoneNumber(instructorRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", instructorRequest.userRequest().phoneNumber())
            );
        }

        Authority authority = authorityRepository.findByAuthorityName(instructorRequest.userRequest().authorities().get(0).authorityName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Authority with name = %s not found", instructorRequest.userRequest().authorities().get(0).authorityName())
                ));

        Instructor instructor = instructorMapper.toRequest(instructorRequest);
        instructor.setUuid(UUID.randomUUID().toString());
        instructor.setDegree(instructorRequest.degree());
        instructor.setMajor(instructorRequest.major());
        instructor.setHighSchool(instructorRequest.highSchool());
        instructor.setStudyAtUniversityOrInstitution(instructorRequest.studyAtUniversityOrInstitution());
        instructor.setExperienceAtWorkingPlace(instructorRequest.experienceAtWorkingPlace());
        instructor.setExperienceYear(instructorRequest.experienceYear());
        instructor.setDegreeGraduationDate(instructorRequest.degreeGraduationDate());
        instructor.setHighSchoolGraduationDate(instructorRequest.highSchoolGraduationDate());
        instructor.setDeleted(false);
        instructor.setStatus(false);

        User user = new User();
        user.setAlias(instructorRequest.userRequest().alias());
        user.setEmail(instructorRequest.userRequest().email());
        user.setPhoneNumber(instructorRequest.userRequest().phoneNumber());
        user.setPassword(passwordEncoder.encode(instructorRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(instructorRequest.userRequest().gender());
        user.setNameEn(instructorRequest.userRequest().nameEn());
        user.setNameKh(instructorRequest.userRequest().nameKh());
        user.setUsername(instructorRequest.userRequest().username());
        user.setProfileImage(instructorRequest.userRequest().profileImage());
        user.setCityOrProvince(instructorRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(instructorRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(instructorRequest.userRequest().sangkatOrCommune());
        user.setStreet(instructorRequest.userRequest().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(instructorRequest.userRequest().birthPlace().cityOrProvince());
        birthPlace.setKhanOrDistrict(instructorRequest.userRequest().birthPlace().khanOrDistrict());
        birthPlace.setSangkatOrCommune(instructorRequest.userRequest().birthPlace().sangkatOrCommune());
        birthPlace.setStreet(instructorRequest.userRequest().birthPlace().street());
        user.setBirthPlace(birthPlace);

        // Save the Authority entity before associating it with the User entity
        authority.setAuthorityName(instructorRequest.userRequest().authorities().get(0).authorityName());
        user.setAuthorities(List.of(authority));

        user = userRepository.save(user);

        instructor.setUser(user);
        Instructor savedInstructor = instructorRepository.save(instructor);

        return instructorMapper.toResponse(savedInstructor);
    }


    @Override
    public InstructorResponse updateInstructorByUuid(String uuid, InstructorRequest instructorRequest) {

        Instructor instructor = instructorRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Instructor with uuid = %s not found", uuid)
                ));

        User user = userRepository.findByAlias(instructorRequest.userRequest().alias())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s not found", instructorRequest.userRequest().alias())
                ));

        if (userRepository.existsByEmail(instructorRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", instructorRequest.userRequest().email())
            );
        }

        if (userRepository.existsByPhoneNumber(instructorRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", instructorRequest.userRequest().phoneNumber())
            );
        }

        // Update instructor details
        instructor.setDegree(instructorRequest.degree());
        instructor.setMajor(instructorRequest.major());
        instructor.setHighSchool(instructorRequest.highSchool());
        instructor.setStudyAtUniversityOrInstitution(instructorRequest.studyAtUniversityOrInstitution());
        instructor.setExperienceAtWorkingPlace(instructorRequest.experienceAtWorkingPlace());
        instructor.setExperienceYear(instructorRequest.experienceYear());
        instructor.setDegreeGraduationDate(instructorRequest.degreeGraduationDate());
        instructor.setHighSchoolGraduationDate(instructorRequest.highSchoolGraduationDate());
        instructor.setDeleted(false);
        instructor.setStatus(false);

        // Update user details
        user.setAlias(instructorRequest.userRequest().alias());
        user.setEmail(instructorRequest.userRequest().email());
        user.setPhoneNumber(instructorRequest.userRequest().phoneNumber());
        user.setPassword(passwordEncoder.encode(instructorRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(instructorRequest.userRequest().gender());
        user.setNameEn(instructorRequest.userRequest().nameEn());
        user.setNameKh(instructorRequest.userRequest().nameKh());
        user.setUsername(instructorRequest.userRequest().username());
        user.setProfileImage(instructorRequest.userRequest().profileImage());
        user.setCityOrProvince(instructorRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(instructorRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(instructorRequest.userRequest().sangkatOrCommune());
        user.setStreet(instructorRequest.userRequest().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Update birth place
        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(instructorRequest.userRequest().birthPlace().cityOrProvince());
        birthPlace.setKhanOrDistrict(instructorRequest.userRequest().birthPlace().khanOrDistrict());
        birthPlace.setSangkatOrCommune(instructorRequest.userRequest().birthPlace().sangkatOrCommune());
        birthPlace.setVillageOrPhum(instructorRequest.userRequest().birthPlace().villageOrPhum());
        birthPlace.setStreet(instructorRequest.userRequest().birthPlace().street());
        user.setBirthPlace(birthPlace);

        // Update authorities
        List<Authority> authorities = new ArrayList<>();
        for (AuthorityRequestToUser authorityRequest : instructorRequest.userRequest().authorities()) {
            Authority authority = authorityRepository.findByAuthorityName(authorityRequest.authorityName())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Authority with name = %s not found", authorityRequest.authorityName())
                    ));
            authorities.add(authority);
        }
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
        int updated = instructorRepository.updateStatusByUuid(uuid, true);
        if(updated > 0){
            Instructor instructor = instructorRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Instructor with uuid = %s not found", uuid)
                    ));
            return instructorMapper.toResponse(instructor);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Instructor with uuid = %s not found", uuid)
            );
        }
    }

    @Override
    public InstructorResponse enableInstructorByUuid(String uuid) {

        int updated = instructorRepository.updateStatusByUuid(uuid, false);
        if(updated > 0) {
            Instructor instructor = instructorRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Instructor with uuid = %s not found", uuid)
                    ));
            return instructorMapper.toResponse(instructor);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Instructor with uuid = %s not found", uuid)
            );
        }
    }

    @Override
    public InstructorResponse blockInstructorByUuid(String uuid) {

        int updatedBlock = instructorRepository.updateDeletedStatusByUuid(uuid, true);
        if(updatedBlock > 0) {
            Instructor instructor = instructorRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Instructor with uuid = %s not found", uuid)
                    ));
            return instructorMapper.toResponse(instructor);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Instructor with uuid = %s not found", uuid)
            );
        }
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
