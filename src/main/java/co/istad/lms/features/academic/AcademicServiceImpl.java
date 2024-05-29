package co.istad.lms.features.academic;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Academic;
import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestDetail;
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
public class AcademicServiceImpl implements AcademicService {

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

    private void validateUser( AcademicRequest academicRequest) {

        if (userRepository.existsByEmailOrUsername(academicRequest.user().email() , academicRequest.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", academicRequest.user().email())
            );
        }

        if (userRepository.existsByUsername(academicRequest.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with alias = %s already exists", academicRequest.user().username())
            );
        }

    }


    @Override
    public AcademicResponse createAcademic(AcademicRequest academicRequest) {

        // validation user
        validateUser(academicRequest);

        // Create academic by mapping
        Academic academic = academicMapper.toRequest(academicRequest);
        academic.setUuid(UUID.randomUUID().toString());
        academic.setDeleted(false);
        academic.setStatus(false);

        // Create user by mapping
        User user = userMapper.fromUserRequest(academicRequest.user());
        // set user details
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(academicRequest.user().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // set authorities to user that we get from the getAuthorities method
        List<Authority> authorities = getAuthorities(academicRequest.user().authorities());
        user.setAuthorities(authorities);

        //save user and academic
        userRepository.save(user);
        // set user to academic
        academic.setUser(user);
        // save information to academic
        Academic savedAcademic = academicRepository.save(academic);

        return academicMapper.toResponse(savedAcademic);
    }

    @Override
    public AcademicResponse updateAcademicByUuid(String uuid, AcademicRequestDetail academicRequestDetail) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        User user = userRepository.findByUuid(academicRequestDetail.user().uuid())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s not found", academicRequestDetail.user().uuid())
                ));

        if (userRepository.existsByEmailOrUsername(academicRequestDetail.user().email() , academicRequestDetail.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", academicRequestDetail.user().email())
            );
        }

        if (userRepository.existsByUsername(academicRequestDetail.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with user name = %S already exists ", academicRequestDetail.user().username())
            );
        }


        // Update academic details
        academic.setDegree(academicRequestDetail.degree());
        academic.setMajor(academicRequestDetail.major());
        academic.setHighSchool(academicRequestDetail.highSchool());
        academic.setStudyAtUniversityOrInstitution(academicRequestDetail.studyAtUniversityOrInstitution());
        academic.setExperienceAtWorkingPlace(academicRequestDetail.experienceAtWorkingPlace());
        academic.setExperienceYear(academicRequestDetail.experienceYear());
        academic.setDegreeGraduationDate(academicRequestDetail.degreeGraduationDate());
        academic.setHighSchoolGraduationDate(academicRequestDetail.highSchoolGraduationDate());
        academic.setDeleted(false);
        academic.setStatus(false);

        // Update user details
        user.setEmail(academicRequestDetail.user().email());
        user.setPhoneNumber(academicRequestDetail.user().phoneNumber());
        user.setPassword(passwordEncoder.encode(academicRequestDetail.user().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(academicRequestDetail.user().gender());
        user.setNameEn(academicRequestDetail.user().nameEn());
        user.setNameKh(academicRequestDetail.user().nameKh());
        user.setUsername(academicRequestDetail.user().username());
        user.setProfileImage(academicRequestDetail.user().profileImage());
        user.setCityOrProvince(academicRequestDetail.user().cityOrProvince());
        user.setKhanOrDistrict(academicRequestDetail.user().khanOrDistrict());
        user.setSangkatOrCommune(academicRequestDetail.user().sangkatOrCommune());
        user.setStreet(academicRequestDetail.user().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Update birth place
        user.setBirthPlace(toBirthPlace(academicRequestDetail.user().birthPlace()));

        // Update authorities
        List<Authority> authorities = getAuthorities(academicRequestDetail.user().authorities());
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

        Page<Academic> academicResponses = new PageImpl<>(academicList, pageRequest, academicList.size());
        return academicResponses.map(academicMapper::toResponse);
    }


}
