package co.istad.lms.features.admin;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.*;
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


    public void createAdmin(@Valid AdminRequest adminRequest) {
        // Check if the user already exists
        if (userRepository.existsByEmailOrUsername(adminRequest.email(), adminRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s already exists", adminRequest.email(), adminRequest.username()));
        }

        // Map adminRequest to Admin entity using MapStruct
        Admin admin = adminMapper.toRequestAdmin(adminRequest);
        admin.setUuid(UUID.randomUUID().toString());

        // Map adminRequest to User entity using MapStruct
        User user = userMapper.fromAdminRequest(adminRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(adminRequest.password()));
        user.setStatus(false);
        user.setIsDeleted(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Fetch and assign authorities
        Set<Authority> allAuthorities = new HashSet<>();
        for (String authorityName : adminRequest.authorityNames()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(authorityName);
            allAuthorities.addAll(foundAuthorities);
        }
        user.setAuthorities(allAuthorities);

        // Save the user and admin to the database
        userRepository.save(user);
        admin.setUser(user);
        adminRepository.save(admin);
    }

    @Override
    public AdminResponseDetail updateAdminByUuid(String uuid, AdminRequestUpdate adminRequestDetail) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with UUID = %s not found", uuid)));

        // Check if the user with the provided email or username already exists (excluding current user)
        if (userRepository.existsByEmailOrUsernameAndUuidNot(adminRequestDetail.email(), adminRequestDetail.username(), user.getUuid())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User with email = %s or username = %s already exists", adminRequestDetail.email(), adminRequestDetail.username()));
        }

        // Update the user fields from the admin request
        userMapper.updateUserFromAdminRequest(user, adminRequestDetail);

        // Save the updated user entity
        userRepository.save(user);

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Set the updated user to the admin and save
        admin.setUser(user);
        adminMapper.updateAdminFromRequest(admin, adminRequestDetail);
        Admin savedAdmin = adminRepository.save(admin);

        // Return the updated admin details
        return adminMapper.toAdminResponseDetail(savedAdmin);


    }

    @Override
    public Page<AdminResponseDetail> getAdminsDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Admin> admins = adminRepository.findAll(pageRequest);
        List<Admin> filteredAdmins = admins.stream()
                .filter(admin -> !admin.getUser().getStatus())
                .filter(admin -> !admin.getUser().getIsDeleted())
                .toList();

        return new PageImpl<>(filteredAdmins, pageRequest, filteredAdmins.size())
                .map(adminMapper::toAdminResponseDetail);
    }

    @Override
    public Page<AdminResponse> getAdmins(int page, int limit) {

        // Create a page request with the page and limit and sort by id
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));

        // Find all the admins from the database
        Page<Admin> admins = adminRepository.findAll(pageRequest);

        // Filter the admins that are not deleted and are not disabled
        List<Admin> filteredAdmins = admins.stream()
                .filter(admin -> !admin.getUser().getIsDeleted())
                .filter(admin -> !admin.getUser().getStatus())
                .toList();

        return new PageImpl<>(filteredAdmins, pageRequest, filteredAdmins.size())
                .map(adminMapper::toAdminResponse);

    }


    @Override
    public AdminResponseDetail getAdminDetailByUuid(String uuid) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)));

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Map the admin entity to AdminResponseDetail DTO
        return adminMapper.toAdminResponseDetail(admin);

    }

    @Override
    public AdminResponse getAdminByUuid(String uuid) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)));

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Map the admin entity to AdminResponse DTO
        return adminMapper.toAdminResponse(admin);

    }


    @Override
    public void deleteAdminByUuid(String uuid) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)));

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Delete the admin that found by the uuid
        adminRepository.delete(admin);

    }

    @Override
    public void disableAdminByUuid(String uuid) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)));

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Set the status of the admin to true
        user.setStatus(true);
        // Save the admin to the database that found by the uuid
        adminRepository.save(admin);


    }

    @Override
    public void enableAdminByUuid(String uuid) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)));

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Set the status of the admin to false
        user.setStatus(false);
        // Save the admin to the database that found by the uuid

        adminRepository.save(admin);

    }

    @Override
    public void blockAdminByUuid(String uuid) {

        // Find the user by the UUID
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with uuid = %s not found", uuid)));

        // Find the admin associated with the user
        Admin admin = adminRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Admin for user UUID = %s not found", uuid)));

        // Set the deleted(is boolean) of the admin to true , don't delete from the database just set the deleted(disable on the user account)
        user.setIsDeleted(true);
        // Save the admin to the database that found by the uuid
        adminRepository.save(admin);


    }


}
