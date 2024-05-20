package co.istad.lms.features.user;



import co.istad.lms.domain.Role;
import co.istad.lms.domain.User;
import co.istad.lms.features.role.RoleRepository;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> getAllUsers(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit);

        Page<User> users = userRepository.findAll(pageRequest);

        return users.map(userMapper::toUserResponse);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !user.getIsDeleted())
                .filter(user -> !user.getIsBlocked())
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found ! "
                        ));

        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User email has already been existed!"
            );
        }
        if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User phone number has already been existed!"
            );
        }
        User user = userMapper.fromUserRequest(userRequest);

        // Set additional properties
        String alias = UUID.randomUUID().toString();
        user.setAlias(alias);
        user.setUserName(userRequest.userName());
        user.setName_en(userRequest.name_en());
        user.setName_kh(userRequest.name_kh());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encode password
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setGender(userRequest.gender());
        user.setProfileImage(userRequest.profileImage());
        user.setCityOrProvince(userRequest.cityOrProvince());
        user.setKhanOrDistrict(userRequest.khanOrDistrict());
        user.setSangkatOrCommune(userRequest.sangkatOrCommune());
        user.setStreet(userRequest.street());
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);


        List<Role> roles = new ArrayList<>();
        // Create dynamic role from client
        userRequest.roles().forEach(r -> {
            Role newRole = roleRepository.findByRoleName(r.roleName())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Role USER has not been found!"));
            roles.add(newRole);
        });

        user.setRoles(roles);


        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(userRepository.save(savedUser));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        System.out.println(" User Name : " + user.getUserName());
        // Update user fields
        user.setUserName(userRequest.userName());
        user.setName_en(userRequest.name_en());
        user.setName_kh(userRequest.name_kh());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setPhoneNumber(passwordEncoder.encode(userRequest.phoneNumber()));
        user.setGender(userRequest.gender());
        user.setProfileImage(userRequest.profileImage());
        user.setCityOrProvince(userRequest.cityOrProvince());
        user.setKhanOrDistrict(userRequest.khanOrDistrict());
        user.setSangkatOrCommune(userRequest.sangkatOrCommune());
        user.setStreet(userRequest.street());

        // Update roles
        List<Role> roles = new ArrayList<>();
        userRequest.roles().forEach(r -> {
            Role newRole = roleRepository.findByRoleName(r.roleName())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Role not found"));
            roles.add(newRole);
        });
        user.setRoles(roles);

        User updatedUser = userRepository.save(user);

        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public UserResponse deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User has not been found ! "
                        ));
        userRepository.deleteById(id);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse disableUser(Long id) {

        int affectedRow = userRepository.updateBlockedStatusById(id, true);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findById(id)
                            .orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id = " + id + " doesn't exist ! "
            );
        }
    }

    @Override
    public UserResponse enableUser(Long  id) {
        int affectedRow = userRepository.updateBlockedStatusById(id, false);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findById(id)
                            .orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id = " + id + " doesn't exist ! "
            );
        }
    }

    @Override
    public UserResponse isDeleted(Long id) {
        int affectedRow = userRepository.updateDeletedStatusById(id, true);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findById(id)
                            .orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id = " + id + " doesn't exist ! "
            );
        }
    }

}
