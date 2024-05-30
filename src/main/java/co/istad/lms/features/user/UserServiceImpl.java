package co.istad.lms.features.user;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.domain.json.BirthPlace;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.user.dto.JsonBirthPlace;
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


    // Validate if user exists by email or phone number
    private void validateUserExists(UserRequest userRequest) {

        // Check if user exists by email
        if (userRepository.existsByEmailOrUsername(userRequest.email() , userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User email = %s has already been existed!", userRequest.email()));
        }


    }


    // Convert BirthPlace
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

    // Set user authorities
    private void setUserAuthorities(User user, UserRequest userRequest) {

        // Get authorities
        List<Authority> authorities = new ArrayList<>();
        // Loop through authorities
        userRequest.authorities().forEach(r -> {
            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    String.format("Role with name = %s was not found.", r.authorityName())
                            )
                    );

            //
            authorities.add(authority);
        });
//        user.setAuthorities(authorities);
    }


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
    public UserResponse getUserById(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        validateUserExists(userRequest);

        User user = userMapper.fromUserRequest(userRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
//        user.setBirthPlace(toBirthPlace(userRequest.birthPlace()));
        setUserAuthorities(user, userRequest);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(String uuid, UserRequest userRequest) {

        // Check if user exists by email
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        setUserAuthorities(user, userRequest);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(String uuid) {

        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        userRepository.delete(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse disableUser(String alias) {

        User user = userRepository.findByUuid(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));
        user.setIsBlocked(true);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
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
