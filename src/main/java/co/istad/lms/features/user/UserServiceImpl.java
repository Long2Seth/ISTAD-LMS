package co.istad.lms.features.user;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import co.istad.lms.features.user.dto.*;
import co.istad.lms.mapper.UserMapper;
import jakarta.transaction.Transactional;
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

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;


    @Override
    public Page<UserResponse> getAllUsers(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> users = userRepository.findAll(pageRequest);

        List<User> filteredUsers = users.stream()
                .filter(user -> !user.getIsDeleted())
                .filter(user -> !user.getStatus())
                .toList();

        return new PageImpl<>(filteredUsers, pageRequest, filteredUsers.size()).map(userMapper::toUserResponse);
    }

    @Override
    public Page<UserResponseDetail> getAllUsersDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<User> users = userRepository.findAll(pageRequest);

        List<User> filteredUsers = users.stream()
                .filter(user -> !user.getIsDeleted())
                .filter(user -> !user.getStatus())
                .toList();

        return new PageImpl<>(filteredUsers, pageRequest, filteredUsers.size()).map(userMapper::toUserResponseDetail);
    }


    @Override
    public Page<UserResponse> getAllUsersWithAdminRole(int page, int limit) {

        // Get all users with admin role that sorted by created date
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<User> users = userRepository.findAllUsersWithAdminRole(pageRequest);

        return users.map(userMapper::toUserResponse);
    }


    @Override
    public UserResponse getUserById(String uuid) {

        // Check if user exists by email if not throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        return userMapper.toUserResponse(user);

    }

    @Override
    public UserResponseDetail getUserDetailById(String uuid) {

        // Check if user exists by email if not throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        return userMapper.toUserResponseDetail(user);

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        // Validate if user exists by email or username
        if (userRepository.existsByEmailOrUsername(userRequest.email(), userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User email = %s has already been existed!", userRequest.email()));
        }

        // Map user request to user
        User user = userMapper.fromUserRequest(userRequest);
        // Set user uuid
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setIsDeleted(false);
        user.setStatus(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        // Set user authorities that get authorities by authority name
        Set<Authority> allAuthorities = new HashSet<>();
        for (String authorityName : userRequest.authorityNames()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(authorityName);
            allAuthorities.addAll(foundAuthorities);
        }

        // Set user authorities that found by authority name
        user.setAuthorities(allAuthorities);

        // return user response after save user
        return userMapper.toUserResponse(userRepository.save(user));

    }

    @Override
    public UserResponse updateUser(String uuid, UserUpdateRequest userRequest) {

        // Check if user exists by email that not found throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        // Update user from request object that map user request to user
        userMapper.updateUserFromRequest(user, userRequest);

        // Set the authorities of the user from the authorities of the adminRequest
//        Set<Authority> allAuthorities = new HashSet<>();
//        if (userRequest.authorities() == null || userRequest.authorities().isEmpty()) {
//            for (Authority request : user.getAuthorities()) {
//                Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.getAuthorityName());
//                allAuthorities.addAll(foundAuthorities);
//            }
//        } else {
//            for (AuthorityRequestToUser request : userRequest.authorities()) {
//                Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
//                allAuthorities.addAll(foundAuthorities);
//            }
//            user.setAuthorities(allAuthorities);
//        }

        return userMapper.toUserResponse(userRepository.save(user));

    }

    @Override
    public void deleteUser(String uuid) {

        // Check if user exists by email that not found throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        // Delete user by hard delete
        userRepository.delete(user);

        userMapper.toUserResponse(user);

    }

    @Override
    public void disableUser(String alias) {

        // Check if user exists by email that not found throw exception
        User user = userRepository.findByUuid(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));

        // Change boolean isBlocked to true that user is blocked
        user.setStatus(true);
        // Return user response after save user
        userRepository.save(user);

    }

    @Override
    public void enableUser(String alias) {

        // Check if user exists by email that not found throw exception
        User user = userRepository.findByUuid(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));

        // Change boolean isBlocked to false that user is not blocked
        user.setStatus(false);
        // Return user response after save user
        userRepository.save(user);

    }

    @Override
    public void isDeleted(String uuid) {

        // Check if user exists by email that not found throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        // Change boolean isDeleted to true that user is deleted(soft delete)
        user.setIsDeleted(true);
        // Return user response after save user
        userRepository.save(user);

    }


}
