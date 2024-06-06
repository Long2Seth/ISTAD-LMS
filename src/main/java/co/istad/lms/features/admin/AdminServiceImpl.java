package co.istad.lms.features.admin;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestDetail;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.admin.dto.AdminResponseDetail;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequest;
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

import java.util.*;

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
    public void createAdmin(@Valid AdminRequest adminRequest) {
        // Check if the user already exists
        if (userRepository.existsByEmailOrUsername(adminRequest.user().email(), adminRequest.user().username())) {
            log.error("User with email = {} or username = {} already exists", adminRequest.user().email(), adminRequest.user().username());
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s already exists", adminRequest.user().email(), adminRequest.user().username()));
        }

        // Map adminRequest to Admin entity
        Admin admin = adminMapper.toRequestAdmin(adminRequest);
        admin.setUuid(UUID.randomUUID().toString());
        admin.setDeleted(false);
        admin.setStatus(false);

        // Map user part of the request to User entity
        User user = userMapper.fromUserRequest(adminRequest.user());
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(adminRequest.user().password()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Fetch and assign authorities
        Set<Authority> allAuthorities = new HashSet<>();
        for (AuthorityRequestToUser request : adminRequest.user().authorities()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
            if (foundAuthorities.isEmpty()) {
                // Handle the case where no authorities are found
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Authority %s not found", request.authorityName()));
            }
            allAuthorities.addAll(foundAuthorities);
        }

        // Set the authorities of the user
        user.setAuthorities(allAuthorities);

        // Set user to admin and save
        admin.setUser(user);

        // Save the admin to the database
        adminRepository.save(admin);

    }


    @Override
    public AdminResponseDetail updateAdminByUuid(String uuid, AdminRequestDetail adminRequestDetail) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Find the user by the uuid
        User user = userRepository.findByUuid(admin.getUser().getUuid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", admin.getUser().getUuid())));

        // Check if the user with the email already exists
        if (userRepository.existsByEmailOrUsername(adminRequestDetail.user().email(), adminRequestDetail.user().username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s have already exists", adminRequestDetail.user().email(), adminRequestDetail.user().username()));
        }

        //update the admin that found by the uuid
        adminMapper.updateAdminFromRequest(admin, adminRequestDetail);
        if (adminRequestDetail.user().password() != null) {
            user.setPassword(passwordEncoder.encode(adminRequestDetail.user().password()));
        }

        // Set the authorities of the user from the authorities of the adminRequest
        Set<Authority> allAuthorities = new HashSet<>();
        for (AuthorityRequestToUser request : adminRequestDetail.user().authorities()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
            System.out.println("foundAuthorities = " + foundAuthorities);
            allAuthorities.addAll(foundAuthorities);
        }
        user.setAuthorities(allAuthorities);

        // Set the data of field the user by the userRequest
        userRepository.save(user);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toAdminResponseDetail(savedAdmin);
    }


    @Override
    public Page<AdminResponseDetail> getAdminsDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Admin> admins = adminRepository.findAll(pageRequest);
        List<Admin> filteredAdmins = admins.stream()
                .filter(admin -> !admin.isDeleted())
                .filter(admin -> !admin.isStatus())
                .toList();
        return new PageImpl<>(filteredAdmins, pageRequest, filteredAdmins.size())
                .map(adminMapper::toAdminResponseDetail);

    }

    @Override
    public Page<AdminResponse> getAdmins(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Admin> admins = adminRepository.findAll(pageRequest);
        List<Admin> filteredAdmins = admins.stream()
                .filter(admin -> !admin.isDeleted())
                .filter(admin -> !admin.isStatus())
                .toList();
        return new PageImpl<>(filteredAdmins, pageRequest, filteredAdmins.size())
                .map(adminMapper::toAdminResponse);

    }


    @Override
    public AdminResponseDetail getAdminDetailByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        return adminMapper.toAdminResponseDetail(admin);
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
    public void disableAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Set the status of the admin to true
        admin.setStatus(true);
        // Save the admin to the database that found by the uuid
        adminRepository.save(admin);


    }

    @Override
    public void enableAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Set the status of the admin to false
        admin.setStatus(false);
        // Save the admin to the database that found by the uuid

        adminRepository.save(admin);

    }

    @Override
    public void blockAdminByUuid(String uuid) {

        // Find the admin by the uuid
        Admin admin = adminRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin with uuid = %s not found", uuid)));

        // Set the deleted(is boolean) of the admin to true , don't delete from the database just set the deleted(disable on the user account)
        admin.setDeleted(true);
        // Save the admin to the database that found by the uuid
        adminRepository.save(admin);


    }


}
