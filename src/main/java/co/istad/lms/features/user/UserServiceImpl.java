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





    // Set user authorities
//    private void setUserAuthorities( UserRequest userRequest) {
//
//        // Get authorities
//        List<Authority> authorities = new ArrayList<>();
//        // Loop through authorities
//        userRequest.authorities().forEach(r -> {
//            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
//                    .orElseThrow(
//                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                    String.format("Role with name = %s was not found.", r.authorityName())
//                            )
//                    );
//
//            //
//            authorities.add(authority);
//        });
////        user.setAuthorities(authorities);
//    }




    @Override
    public Page<UserResponse> getAllUsers(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> users = userRepository.findAll(pageRequest);

        List<User> filteredUsers = users.stream()
                .filter(user -> !user.getIsDeleted())
                .filter(user -> !user.getIsBlocked())
                .toList();

        return new PageImpl<>(filteredUsers, pageRequest, filteredUsers.size()).map(userMapper::toUserResponse);
    }

    @Override
    public Page<UserResponseDetail> getAllUsersDetail(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<User> users = userRepository.findAll(pageRequest);

        List<User> filteredUsers = users.stream()
                .filter(user -> !user.getIsDeleted())
                .filter(user -> !user.getIsBlocked())
                .toList();

        return new PageImpl<>(filteredUsers, pageRequest, filteredUsers.size()).map(userMapper::toUserResponseDetail);
    }


    @Override
    public Page<UserResponse> getAllUsersWithAdminRole(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<User> users = userRepository.findAllUsersWithAdminRole(pageRequest);
        return users.map(userMapper::toUserResponse);
    }


    @Override
    public UserResponse getUserById(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponseDetail getUserDetailById(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        return userMapper.toUserResponseDetail(user);

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        // Validate if user exists
        if (userRepository.existsByEmailOrUsername(userRequest.email() , userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User email = %s has already been existed!", userRequest.email()));
        }

        // Map user request to user
        User user = userMapper.fromUserRequest(userRequest);
        // Set user uuid
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setIsDeleted(false);
        user.setIsBlocked(false);
        user.setIsChangePassword(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Set<Authority> allAuthorities = new HashSet<>();
        for (AuthorityRequestToUser request : userRequest.authorities()) {
            Set<Authority> foundAuthorities = authorityRepository.findAllByAuthorityName(request.authorityName());
            System.out.println("foundAuthorities = " + foundAuthorities);
            allAuthorities.addAll(foundAuthorities);
        }
        user.setAuthorities(allAuthorities);

        return userMapper.toUserResponse(userRepository.save(user));


    }

    @Override
    public UserResponse updateUser(String uuid, UserUpdateRequest userRequest) {

        // Check if user exists by email
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        userMapper.updateUserFromRequest(user, userRequest);

        return userMapper.toUserResponse(userRepository.save(user));

    }

    @Override
    public void deleteUser(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        userRepository.delete(user);

        userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse disableUser(String alias) {

        User user = userRepository.findByUuid(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));
        user.setIsBlocked(true);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse enableUser(String alias) {

        User user = userRepository.findByUuid(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));

        user.setIsBlocked(false);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse isDeleted(String alias) {

        User user = userRepository.findByUuid(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));

        user.setIsDeleted(true);

        return userMapper.toUserResponse(userRepository.save(user));
    }


}
