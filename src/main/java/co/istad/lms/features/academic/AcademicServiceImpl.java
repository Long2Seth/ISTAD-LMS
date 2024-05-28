package co.istad.lms.features.academic;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Academic;
import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicResponse;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.mapper.AcademicMapper;
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
public class AcademicServiceImpl implements AcademicService{

    private final AcademicRepository academicRepository;
    private final AcademicMapper academicMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;



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


    // This method is used get list of authorities from the authorityRequests
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



    @Override
    public AcademicResponse createAcademic(AcademicRequest academicRequest) {

        if (userRepository.existsByEmail(academicRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", academicRequest.userRequest().email())
            );
        }

        if (userRepository.existsByAlias(academicRequest.userRequest().alias())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with alias = %s already exists", academicRequest.userRequest().alias())
            );
        }

        if (userRepository.existsByPhoneNumber(academicRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", academicRequest.userRequest().phoneNumber())
            );
        }


        Academic academic = academicMapper.toRequest(academicRequest);
        academic.setUuid(UUID.randomUUID().toString());
        academic.setDeleted(false);
        academic.setStatus(false);

        User user = userMapper.fromUserRequest(academicRequest.userRequest());
        user.setPassword(passwordEncoder.encode(academicRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setBirthPlace(toBirthPlace(academicRequest.userRequest().birthPlace()));
        List<Authority> authorities = getAuthorities(academicRequest.userRequest().authorities());
        user.setAuthorities(authorities);
        userRepository.save(user);
        academic.setUser(user);
        Academic savedAcademic = academicRepository.save(academic);

        return academicMapper.toResponse(savedAcademic);
    }

    @Override
    public AcademicResponse updateAcademicByUuid(String uuid, AcademicRequest academicRequest) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        User user = userRepository.findByAlias(academicRequest.userRequest().alias())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s not found", academicRequest.userRequest().alias())
                ));

        if (userRepository.existsByEmail(academicRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", academicRequest.userRequest().email())
            );
        }

        if (userRepository.existsByPhoneNumber(academicRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", academicRequest.userRequest().phoneNumber())
            );
        }

        // Update academic details
        academic.setDegree(academicRequest.degree());
        academic.setMajor(academicRequest.major());
        academic.setHighSchool(academicRequest.highSchool());
        academic.setStudyAtUniversityOrInstitution(academicRequest.studyAtUniversityOrInstitution());
        academic.setExperienceAtWorkingPlace(academicRequest.experienceAtWorkingPlace());
        academic.setExperienceYear(academicRequest.experienceYear());
        academic.setDegreeGraduationDate(academicRequest.degreeGraduationDate());
        academic.setHighSchoolGraduationDate(academicRequest.highSchoolGraduationDate());
        academic.setDeleted(false);
        academic.setStatus(false);

        // Update user details
        user.setAlias(academicRequest.userRequest().alias());
        user.setEmail(academicRequest.userRequest().email());
        user.setPhoneNumber(academicRequest.userRequest().phoneNumber());
        user.setPassword(passwordEncoder.encode(academicRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(academicRequest.userRequest().gender());
        user.setNameEn(academicRequest.userRequest().nameEn());
        user.setNameKh(academicRequest.userRequest().nameKh());
        user.setUsername(academicRequest.userRequest().username());
        user.setProfileImage(academicRequest.userRequest().profileImage());
        user.setCityOrProvince(academicRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(academicRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(academicRequest.userRequest().sangkatOrCommune());
        user.setStreet(academicRequest.userRequest().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Update birth place
        user.setBirthPlace(toBirthPlace(academicRequest.userRequest().birthPlace()));

        // Update authorities
        List<Authority> authorities = getAuthorities(academicRequest.userRequest().authorities());
        user.setAuthorities(authorities);
        // Save the updated user and academic
        userRepository.save(user);
        academic.setUser(user);
        Academic saveAcademic = academicRepository.save(academic);

        return academicMapper.toResponse(saveAcademic);
    }

    @Override
    public AcademicResponse getAcademicByUuid(String uuid) {
        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        return academicMapper.toResponse(academic);

    }

    @Override
    public AcademicResponse deleteAcademicByUuid(String uuid) {
        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academicRepository.delete(academic);
        return academicMapper.toResponse(academic);

    }

    @Override
    public AcademicResponse updateDisableAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academic.setStatus(false);
        return academicMapper.toResponse(academicRepository.save(academic));

    }

    @Override
    public AcademicResponse updateEnableAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academic.setStatus(true);
        return academicMapper.toResponse(academicRepository.save(academic));

    }

    @Override
    public AcademicResponse updateDeletedAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academic.setDeleted(true);
        return academicMapper.toResponse(academicRepository.save(academic));

    }

    @Override
    public Page<AcademicResponse> getAcademics(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Academic> academics = academicRepository.findAll(pageRequest);

        List<Academic> academicList = academics.stream()
                .filter(academic -> !academic.isDeleted())
                .filter(academic -> !academic.isStatus())
                .toList();

        Page<Academic> academicResponses = new PageImpl<>(academicList , pageRequest, academicList.size());
        return academicResponses.map(academicMapper::toResponse);
    }


}
