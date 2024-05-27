package co.istad.lms.features.admin;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponse createAdmin(AdminRequest adminRequest) {

        if (userRepository.existsByEmail(adminRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", adminRequest.userRequest().email())
            );
        }

        if (userRepository.existsByAlias(adminRequest.userRequest().alias())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with alias = %s already exists", adminRequest.userRequest().alias())
            );
        }

        if (userRepository.existsByPhoneNumber(adminRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", adminRequest.userRequest().phoneNumber())
            );
        }


        Admin admin = adminMapper.toRequestAdmin(adminRequest);
        admin.setUuid(UUID.randomUUID().toString());
        admin.setDegree(adminRequest.degree());
        admin.setMajor(adminRequest.major());
        admin.setHighSchool(adminRequest.highSchool());
        admin.setStudyAtUniversityOrInstitution(adminRequest.studyAtUniversityOrInstitution());
        admin.setExperienceAtWorkingPlace(adminRequest.experienceAtWorkingPlace());
        admin.setExperienceYear(adminRequest.experienceYear());
        admin.setDegreeGraduationDate(adminRequest.degreeGraduationDate());
        admin.setHighSchoolGraduationDate(adminRequest.highSchoolGraduationDate());
        admin.setDeleted(false);
        admin.setStatus(false);

        User user = new User();
        user.setAlias(adminRequest.userRequest().alias());
        user.setEmail(adminRequest.userRequest().email());
        user.setPhoneNumber(adminRequest.userRequest().phoneNumber());
        user.setPassword(passwordEncoder.encode(adminRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(adminRequest.userRequest().gender());
        user.setNameEn(adminRequest.userRequest().nameEn());
        user.setNameKh(adminRequest.userRequest().nameKh());
        user.setUsername(adminRequest.userRequest().username());
        user.setProfileImage(adminRequest.userRequest().profileImage());
        user.setCityOrProvince(adminRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(adminRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(adminRequest.userRequest().sangkatOrCommune());
        user.setStreet(adminRequest.userRequest().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(adminRequest.userRequest().birthPlace().cityOrProvince());
        birthPlace.setKhanOrDistrict(adminRequest.userRequest().birthPlace().khanOrDistrict());
        birthPlace.setVillageOrPhum(adminRequest.userRequest().birthPlace().villageOrPhum());
        birthPlace.setHouseNumber(adminRequest.userRequest().birthPlace().houseNumber());
        birthPlace.setSangkatOrCommune(adminRequest.userRequest().birthPlace().sangkatOrCommune());
        birthPlace.setStreet(adminRequest.userRequest().birthPlace().street());
        user.setBirthPlace(birthPlace);

        // Save the Authority entity before associating it with the User entity
        List<Authority> authorities = new ArrayList<>();

        adminRequest.userRequest().authorities().forEach(r -> {
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
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);
    }

    @Override
    public AdminResponse updateAdminByUuid(String uuid, AdminRequest adminRequest) {
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)
                ));

        User user = userRepository.findByAlias(adminRequest.userRequest().alias())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s not found", adminRequest.userRequest().alias())
                ));

        if (userRepository.existsByEmail(adminRequest.userRequest().email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", adminRequest.userRequest().email())
            );
        }

        if (userRepository.existsByPhoneNumber(adminRequest.userRequest().phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", adminRequest.userRequest().phoneNumber())
            );
        }

        // Update admin details
        admin.setDegree(adminRequest.degree());
        admin.setMajor(adminRequest.major());
        admin.setHighSchool(adminRequest.highSchool());
        admin.setStudyAtUniversityOrInstitution(adminRequest.studyAtUniversityOrInstitution());
        admin.setExperienceAtWorkingPlace(adminRequest.experienceAtWorkingPlace());
        admin.setExperienceYear(adminRequest.experienceYear());
        admin.setDegreeGraduationDate(adminRequest.degreeGraduationDate());
        admin.setHighSchoolGraduationDate(adminRequest.highSchoolGraduationDate());
        admin.setDeleted(false);
        admin.setStatus(false);

        // Update user details
        user.setAlias(adminRequest.userRequest().alias());
        user.setEmail(adminRequest.userRequest().email());
        user.setPhoneNumber(adminRequest.userRequest().phoneNumber());
        user.setPassword(passwordEncoder.encode(adminRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setGender(adminRequest.userRequest().gender());
        user.setNameEn(adminRequest.userRequest().nameEn());
        user.setNameKh(adminRequest.userRequest().nameKh());
        user.setUsername(adminRequest.userRequest().username());
        user.setProfileImage(adminRequest.userRequest().profileImage());
        user.setCityOrProvince(adminRequest.userRequest().cityOrProvince());
        user.setKhanOrDistrict(adminRequest.userRequest().khanOrDistrict());
        user.setSangkatOrCommune(adminRequest.userRequest().sangkatOrCommune());
        user.setStreet(adminRequest.userRequest().street());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Update birth place
        BirthPlace birthPlace = new BirthPlace();
        birthPlace.setCityOrProvince(adminRequest.userRequest().birthPlace().cityOrProvince());
        birthPlace.setKhanOrDistrict(adminRequest.userRequest().birthPlace().khanOrDistrict());
        birthPlace.setSangkatOrCommune(adminRequest.userRequest().birthPlace().sangkatOrCommune());
        birthPlace.setVillageOrPhum(adminRequest.userRequest().birthPlace().villageOrPhum());
        birthPlace.setStreet(adminRequest.userRequest().birthPlace().street());
        user.setBirthPlace(birthPlace);

        // Update authorities
        List<Authority> authorities = new ArrayList<>();
        for (AuthorityRequestToUser authorityRequest : adminRequest.userRequest().authorities()) {
            Authority authority = authorityRepository.findByAuthorityName(authorityRequest.authorityName())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Authority with name = %s not found", authorityRequest.authorityName())
                    ));
            authorities.add(authority);
        }
        user.setAuthorities(authorities);

        // Save the updated user and admin
        userRepository.save(user);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);
    }


    @Override
    public Page<AdminResponse> getAdmins(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Admin> admins = adminRepository.findAll(pageRequest);

        List<Admin> filteredAdmins = admins.stream()
                .filter(admin -> !admin.isDeleted())
                .filter(admin -> !admin.isStatus())
                .toList();

        Page<Admin> filteredAdminsPage = new PageImpl<>(filteredAdmins, pageRequest, filteredAdmins.size());

        return filteredAdminsPage.map(adminMapper::toAdminResponse);
    }

    @Override
    public AdminResponse getAdminByUuid(String uuid) {

        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)
                ));
        return adminMapper.toAdminResponse(admin);
    }

    @Override
    public void deleteAdminByUuid(String uuid) {

        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)
                ));
        adminRepository.delete(admin);

    }

    @Override
    public AdminResponse disableAdminByUuid(String uuid) {
        int affectedRow = adminRepository.updateStatusByUuid(uuid, true);
        if (affectedRow > 0) {
            return adminMapper.toAdminResponse(
                    adminRepository.findByUuid(uuid)
                            .orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Admin with uuid = %s doesn't exist ! ", uuid)
            );
        }
    }

    @Override
    public AdminResponse enableAdminByUuid(String uuid) {
        int affectedRow = adminRepository.updateStatusByUuid(uuid, false);

        if (affectedRow > 0) {
            return adminMapper.toAdminResponse(
                    adminRepository.findByUuid(uuid)
                            .orElseThrow(
                                    () -> new ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            String.format("Admin with uuid = %s doesn't exist ! ", uuid)
                                    ))
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Admin with uuid = %s doesn't exist ! ", uuid)
            );
        }

    }

    @Override
    public AdminResponse blockAdminByUuid(String uuid) {
        int affectedRow = adminRepository.updateDeletedStatusByUuid(uuid, true);
        if (affectedRow > 0) {
            return adminMapper.toAdminResponse(
                    adminRepository.findByUuid(uuid)
                            .orElseThrow(
                                    () -> new ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            String.format("Admin with uuid = %s doesn't exist ! ", uuid)
                                    ))
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Admin with uuid = %s doesn't exist ! ", uuid)
            );
        }
    }


}
