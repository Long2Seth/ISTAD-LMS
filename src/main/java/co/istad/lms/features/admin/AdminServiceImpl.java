package co.istad.lms.features.admin;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.mapper.AdminMapper;
import co.istad.lms.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    // This method is used to convert the JsonBirthPlace object to BirthPlace object
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

    private void validateUserRequest(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", userRequest.email()));
        }
        if (userRepository.existsByAlias(userRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with alias = %s already exists", userRequest.alias()));
        }
        if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", userRequest.phoneNumber()));
        }
    }

    @Override
    public AdminResponse createAdmin( @Valid AdminRequest adminRequest) {
        validateUserRequest(adminRequest.userRequest());

        Admin admin = adminMapper.toRequestAdmin(adminRequest);
        admin.setUuid(UUID.randomUUID().toString());
        admin.setDeleted(false);
        admin.setStatus(false);

        User user = userMapper.fromUserRequest(adminRequest.userRequest());
        user.setPassword(passwordEncoder.encode(adminRequest.userRequest().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setBirthPlace(toBirthPlace(adminRequest.userRequest().birthPlace()));

        List<Authority> authorities = getAuthorities(adminRequest.userRequest().authorities());
        user.setAuthorities(authorities);
        userRepository.save(user);

        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);
    }

    private void updateAdminDetails(Admin admin, AdminRequest adminRequest) {
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
    }

    private void updateUserDetails(User user, AdminRequest adminRequest) {
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
        user.setBirthPlace(toBirthPlace(adminRequest.userRequest().birthPlace()));
    }

    @Override
    public AdminResponse updateAdminByUuid ( @Valid String uuid, AdminRequest adminRequest) {
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        User user = userRepository.findByAlias(adminRequest.userRequest().alias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s not found", adminRequest.userRequest().alias())));

        if (userRepository.existsByEmail(adminRequest.userRequest().email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s already exists", adminRequest.userRequest().email()));
        }

        if (userRepository.existsByPhoneNumber(adminRequest.userRequest()   .phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with phone number = %s already exists", adminRequest.userRequest().phoneNumber()));
        }

        updateAdminDetails(admin, adminRequest);
        updateUserDetails(user, adminRequest);

        List<Authority> authorities = getAuthorities(adminRequest.userRequest().authorities());
        user.setAuthorities(authorities);

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));
        return adminMapper.toAdminResponse(admin);
    }

    @Override
    public void deleteAdminByUuid(String uuid) {
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponse disableAdminByUuid(String uuid) {

        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));
        admin.setStatus(true);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toAdminResponse(savedAdmin);
    }

    @Override
    public AdminResponse enableAdminByUuid(String uuid) {

        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));
        admin.setStatus(false);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toAdminResponse(savedAdmin);

    }

    @Override
    public AdminResponse blockAdminByUuid(String uuid) {

        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));
        admin.setDeleted(true);
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);
    }


}
