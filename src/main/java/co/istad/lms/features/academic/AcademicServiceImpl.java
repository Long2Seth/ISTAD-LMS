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

        User user = new User();
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

        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(academicRequest.userRequest().birthPlace().cityOrProvince());
        birthPlace.setKhanOrDistrict(academicRequest.userRequest().birthPlace().khanOrDistrict());
        birthPlace.setVillageOrPhum(academicRequest.userRequest().birthPlace().villageOrPhum());
        birthPlace.setHouseNumber(academicRequest.userRequest().birthPlace().houseNumber());
        birthPlace.setSangkatOrCommune(academicRequest.userRequest().birthPlace().sangkatOrCommune());
        birthPlace.setStreet(academicRequest.userRequest().birthPlace().street());
        user.setBirthPlace(birthPlace);

        List<Authority> authorities = new ArrayList<>();

        academicRequest.userRequest().authorities().forEach(r -> {
            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Role with name = %s was not found.", r.authorityName())
                            )
                    );

            authorities.add(authority);

        });

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
        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(academicRequest.userRequest().birthPlace().cityOrProvince());
        birthPlace.setKhanOrDistrict(academicRequest.userRequest().birthPlace().khanOrDistrict());
        birthPlace.setSangkatOrCommune(academicRequest.userRequest().birthPlace().sangkatOrCommune());
        birthPlace.setVillageOrPhum(academicRequest.userRequest().birthPlace().villageOrPhum());
        birthPlace.setHouseNumber(academicRequest.userRequest().birthPlace().houseNumber());
        birthPlace.setStreet(academicRequest.userRequest().birthPlace().street());
        user.setBirthPlace(birthPlace);

        // Update authorities
        List<Authority> authorities = new ArrayList<>();
        for (AuthorityRequestToUser authorityRequest : academicRequest.userRequest().authorities()) {
            Authority authority = authorityRepository.findByAuthorityName(authorityRequest.authorityName())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Authority with name = %s not found", authorityRequest.authorityName())
                    ));
            authorities.add(authority);
        }
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

        int updateStatus = academicRepository.updateStatusByUuid(uuid, false);
        if(updateStatus>0){
            Academic academic = academicRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Academic with uuid = %s not found", uuid)
                    ));
            return academicMapper.toResponse(academic);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Academic with uuid = %s not found", uuid)
            );
        }

    }

    @Override
    public AcademicResponse updateEnableAcademicByUuid(String uuid) {

        int updateStatus = academicRepository.updateStatusByUuid(uuid, true);
        if(updateStatus>0){
            Academic academic = academicRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Academic with uuid = %s not found", uuid)
                    ));
            return academicMapper.toResponse(academic);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Academic with uuid = %s not found", uuid)
            );
        }

    }

    @Override
    public AcademicResponse updateDeletedAcademicByUuid(String uuid) {

        int updateDeleted = academicRepository.updateDeletedStatusByUuid(uuid, true);
        if(updateDeleted>0){
            Academic academic = academicRepository.findByUuid(uuid)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Academic with uuid = %s not found", uuid)
                    ));
            return academicMapper.toResponse(academic);
        }else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Academic with uuid = %s not found", uuid)
            );
        }

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
