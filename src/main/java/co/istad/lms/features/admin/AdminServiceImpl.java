package co.istad.lms.features.admin;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestDetail;
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
        // Create a new BirthPlace object
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
    public AdminResponse createAdmin( @Valid AdminRequest adminRequest) {

        // Check if the user with the email already exists
        if (userRepository.existsByEmailOrUsername(adminRequest.user().email() , adminRequest.user().username() )) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s and username = %s already exists", adminRequest.user().email() , adminRequest.user().username()));
        }

        // Create a new admin by mapper that maps the AdminRequest to Admin
        Admin admin = adminMapper.toRequestAdmin(adminRequest);
        admin.setUuid(UUID.randomUUID().toString());
        admin.setDeleted(false);
        admin.setStatus(false);

        // Create a new user by mapper that maps the UserRequest to User
        User user = userMapper.fromUserRequest(adminRequest.user());
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(adminRequest.user().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Set the authorities of the user from the authorities of the adminRequest
        List<Authority> authorities = new ArrayList<>();
            Authority authority = authorityRepository.findByAuthorityName(adminRequest.user().authorities().get(0).authorityName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Role with name = %s was not found.", adminRequest.user().authorities().get(0).authorityName())));
        System.out.println( " Authority : " + authority.getAuthorityName() );
            authorities.add(authority);

        user.setAuthorities(authorities);

        // Set the data of field the user by the userRequest
        userRepository.save(user);

        // Set the user of the admin
        admin.setUser(user);

        // Save the admin to the database
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);

    }

    @Override
    public AdminResponse updateAdminByUuid ( String uuid, AdminRequestDetail adminRequestDetail) {
//
//        // Find the admin by the uuid
//        Admin admin = adminRepository.findByUuid(uuid)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        String.format("Admin with uuid = %s not found", uuid)));
//
//        // Find the user by the uuid
//        User user = userRepository.findByUuid(adminRequestDetail.user().uuid())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        String.format("User with alias = %s not found", adminRequestDetail.user().uuid())));
//
//        // Check if the user with the email already exists
//        if (userRepository.existsByEmail(adminRequestDetail.user().email())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    String.format("User with email = %s already exists", adminRequestDetail.user().email()));
//        }
//
//        // Check if the user with the phone number already exists
//        if (userRepository.existsByPhoneNumber(adminRequestDetail.user()   .phoneNumber())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    String.format("User with phone number = %s already exists", adminRequestDetail.user().phoneNumber()));
//        }
//
//        //update the admin that found by the uuid
//        admin.setHighSchool(adminRequestDetail.highSchool());
//        admin.setHighSchoolGraduationDate(adminRequestDetail.highSchoolGraduationDate());
//        admin.setDegree(adminRequestDetail.degree());
//        admin.setDegreeGraduationDate(adminRequestDetail.degreeGraduationDate());
//        admin.setMajor(adminRequestDetail.major());
//        admin.setStudyAtUniversityOrInstitution(adminRequestDetail.studyAtUniversityOrInstitution());
//        admin.setExperienceAtWorkingPlace(adminRequestDetail.experienceAtWorkingPlace());
//        admin.setExperienceYear(adminRequestDetail.experienceYear());
//
//        //update the user that found by the uuid
//        user.setNameEn(adminRequestDetail.user().nameEn());
//        user.setNameKh(adminRequestDetail.user().nameKh());
//        user.setUsername(adminRequestDetail.user().username());
//        user.setGender(adminRequestDetail.user().gender());
//        user.setDob(adminRequestDetail.user().dob());
//        user.setEmail(adminRequestDetail.user().email());
//        user.setPassword(passwordEncoder.encode(adminRequestDetail.user().password()));
//        user.setProfileImage(adminRequestDetail.user().profileImage());
//        user.setPhoneNumber(adminRequestDetail.user().phoneNumber());
//        user.setCityOrProvince(adminRequestDetail.user().cityOrProvince());
//        user.setKhanOrDistrict(adminRequestDetail.user().khanOrDistrict());
//        user.setSangkatOrCommune(adminRequestDetail.user().sangkatOrCommune());
//        user.setStreet(adminRequestDetail.user().street());
//        user.setBirthPlace(toBirthPlace(adminRequestDetail.user().birthPlace()));
//
//        // Set the authorities of the user from the authorities of the adminRequest
//        List<Authority> authorities = new ArrayList<>();
//        for (AuthorityRequestToUser request : adminRequestDetail.user().authorities()) {
//            Authority authority = authorityRepository.findByAuthorityName(request.authorityName())
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                            String.format("Role with name = %s was not found.", request.authorityName())));
//            authorities.add(authority);
//        }
//        user.setAuthorities(authorities);
//
//        // Set the data of field the user by the userRequest
//        userRepository.save(user);
//        admin.setUser(user);
//        Admin savedAdmin = adminRepository.save(admin);
//
//        return adminMapper.toAdminResponse(savedAdmin);
        return null;
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

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        return adminMapper.toAdminResponse(admin);
    }

    @Override
    public void deleteAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Delete the admin that found by the uuid
        adminRepository.delete(admin);
    }

    @Override
    public AdminResponse disableAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Set the status of the admin to true
        admin.setStatus(true);
        // Save the admin to the database that found by the uuid
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);

    }

    @Override
    public AdminResponse enableAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Set the status of the admin to false
        admin.setStatus(false);
        // Save the admin to the database that found by the uuid
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);

    }

    @Override
    public AdminResponse blockAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Set the deleted(is boolean) of the admin to true , don't delete from the database just set the deleted(disable on the user account)
        admin.setDeleted(true);
        // Save the admin to the database that found by the uuid
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponse(savedAdmin);

    }


}
