package co.istad.lms.features.user;



import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                .filter(user -> !user.getIsBlocked())
                .toList();

        Page<User> filteredUsersPage = new PageImpl<>(filteredUsers, pageRequest, filteredUsers.size());

        return filteredUsersPage.map(userMapper::toUserResponse);
    }


    @Override
    public UserResponse getUserById(String alias) {

        User user = userRepository.findByAlias(alias)
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
                    String.format("User email = %s has already been existed!", userRequest.email())
            );
        }
        if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("User phone number = %s has already been existed!", userRequest.phoneNumber())
            );
        }
        User user = userMapper.fromUserRequest(userRequest);

        // Set additional properties
        user.setAlias(userRequest.alias());
        user.setUsername(userRequest.username());
        user.setNameEn(userRequest.nameEn());
        user.setNameKh(userRequest.nameKh());
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


        List<Authority> authorities = new ArrayList<>();

        userRequest.authorities().forEach(r -> {
            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Role USER has not been found!"));

            authorities.add(authority);

        });

        user.setAuthorities(authorities);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(userRepository.save(savedUser));
    }

    @Override
    public UserResponse updateUser(String alias, UserRequest userRequest) {
        User user = userRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        // Update user fields
        user.setAlias(userRequest.alias());
        user.setUsername(userRequest.username());
        user.setNameEn(userRequest.nameEn());
        user.setNameKh(userRequest.nameKh());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setGender(userRequest.gender());
        user.setProfileImage(userRequest.profileImage());
        user.setCityOrProvince(userRequest.cityOrProvince());
        user.setKhanOrDistrict(userRequest.khanOrDistrict());
        user.setSangkatOrCommune(userRequest.sangkatOrCommune());
        user.setStreet(userRequest.street());

        // Update roles
        List<Authority> authorities = new ArrayList<>();
        userRequest.authorities().forEach(r -> {
            Authority newAuthority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Role not found"));
            authorities.add(newAuthority);
        });
        user.setAuthorities(authorities);

        User updatedUser = userRepository.save(user);

        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public UserResponse deleteUser(String alias) {
        User user = userRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)
                ));
        userRepository.delete(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse disableUser(String alias) {

        int affectedRow = userRepository.updateBlockedStatusById(alias, true);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findByAlias(alias)
                            .orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with alias = %s doesn't exist ! ", alias)
            );
        }
    }

    @Override
    public UserResponse enableUser(String alias) {
        int affectedRow = userRepository.updateBlockedStatusById(alias, false);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findByAlias(alias)
                            .orElseThrow(
                                    () -> new ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            String.format("User with alias = %s doesn't exist ! ", alias)
                                    ))
                            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with alias = %s doesn't exist ! ", alias)
            );
        }
    }

    @Override
    public UserResponse isDeleted(String alias) {
        int affectedRow = userRepository.updateDeletedStatusById(alias, true);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findByAlias(alias)
                            .orElseThrow(
                                    () -> new ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            String.format("User with alias = %s doesn't exist ! ", alias)
                                    ))
                            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with alias = %s doesn't exist ! ", alias)
            );
        }
    }

}
