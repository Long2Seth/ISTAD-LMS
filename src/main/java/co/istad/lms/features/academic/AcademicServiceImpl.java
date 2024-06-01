package co.istad.lms.features.academic;


import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Academic;
import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestDetail;
import co.istad.lms.features.academic.dto.AcademicResponse;
import co.istad.lms.features.academic.dto.AcademicResponseDetail;
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

import java.util.*;

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




    @Override
    public AcademicResponseDetail createAcademic(AcademicRequest academicRequest) {

        // validation user
        // check if user with email or username already exists in the database
        if (userRepository.existsByEmailOrUsername(academicRequest.user().email(), academicRequest.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s and username = %s already exists", academicRequest.user().email(), academicRequest.user().username())
            );
        }

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
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // set authorities to user that we get from the getAuthorities method
        Set<Authority> allAuthorities = new HashSet<>();
        for (AuthorityRequestToUser request : academicRequest.user().authorities()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
            System.out.println("foundAuthorities = " + foundAuthorities);
            allAuthorities.addAll(foundAuthorities);
        }
        // set user to academic
        user.setAuthorities(allAuthorities);

        //save user and academic
        userRepository.save(user);
        // set user to academic
        academic.setUser(user);
        // save information to academic
        Academic savedAcademic = academicRepository.save(academic);

        return academicMapper.toResponseDetail(savedAcademic);
    }

    @Override
    public AcademicResponseDetail updateAcademicByUuid(String uuid, AcademicRequestDetail academicRequestDetail) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        User user = userRepository.findByUuid(academic.getUser().getUuid())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", academic.getUser().getUuid())
                ));

        if (userRepository.existsByEmailOrUsername(academicRequestDetail.user().email(), academicRequestDetail.user().username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", academicRequestDetail.user().email())
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
        user.setBirthPlace(toBirthPlace(academicRequestDetail.user().birthPlace()));

        Set<Authority> allAuthorities = new HashSet<>();
        for (AuthorityRequestToUser request : academicRequestDetail.user().authorities()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
            System.out.println("foundAuthorities = " + foundAuthorities);
            allAuthorities.addAll(foundAuthorities);
        }
        // set user to academic
        user.setAuthorities(allAuthorities);

        // Save the updated user and academic
        userRepository.save(user);

        academic.setUser(user);

        Academic saveAcademic = academicRepository.save(academic);

        return academicMapper.toResponseDetail(saveAcademic);
    }

    @Override
    public AcademicResponseDetail getAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));

        return academicMapper.toResponseDetail(academic);

    }

    @Override
    public void deleteAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academicRepository.delete(academic);

        academicMapper.toResponseDetail(academic);

    }

    @Override
    public AcademicResponseDetail updateDisableAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academic.setStatus(true);
        return academicMapper.toResponseDetail(academicRepository.save(academic));

    }

    @Override
    public AcademicResponseDetail updateEnableAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academic.setStatus(false);
        return academicMapper.toResponseDetail(academicRepository.save(academic));

    }

    @Override
    public AcademicResponseDetail updateDeletedAcademicByUuid(String uuid) {

        Academic academic = academicRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Academic with uuid = %s not found", uuid)
                ));
        academic.setDeleted(true);
        return academicMapper.toResponseDetail(academicRepository.save(academic));

    }

    @Override
    public Page<AcademicResponseDetail> getAcademics(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Academic> academics = academicRepository.findAll(pageRequest);

        List<Academic> academicList = academics.stream()
                .filter(academic -> !academic.isDeleted())
                .filter(academic -> !academic.isStatus())
                .toList();

        Page<Academic> academicResponses = new PageImpl<>(academicList, pageRequest, academicList.size());
        return academicResponses.map(academicMapper::toResponseDetail);
    }


}
