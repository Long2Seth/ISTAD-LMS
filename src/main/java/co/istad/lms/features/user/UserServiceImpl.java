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

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;





    private void validateUserExists(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User email = %s has already been existed!", userRequest.email()));
        }

        if (userRepository.existsByPhoneNumber(userRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User phone number = %s has already been existed!", userRequest.phoneNumber()));
        }
    }



    private void setUserDetails(User user, UserRequest userRequest) {
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
        user.setBirthPlace(toBirthPlace(userRequest.birthPlace()));
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
    }



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



    private void setUserAuthorities(User user, UserRequest userRequest) {
        List<Authority> authorities = new ArrayList<>();
        userRequest.authorities().forEach(r -> {
            Authority authority = authorityRepository.findByAuthorityName(r.authorityName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Role with name = %s was not found.", r.authorityName())));
            authorities.add(authority);
        });
        user.setAuthorities(authorities);
    }



    private UserResponse updateUserBlockedStatus(String alias, boolean isBlocked) {
        int affectedRows = userRepository.updateBlockedStatusById(alias, isBlocked);
        return getUserResponseIfAffected(alias, affectedRows);
    }



    private User findUserByAlias(String alias) {
        return userRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));
    }




    private UserResponse getUserResponseIfAffected(String alias, int affectedRows) {
        if (affectedRows > 0) {
            return userMapper.toUserResponse(findUserByAlias(alias));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("User with alias = %s doesn't exist!", alias));
        }
    }






    @Override
    public Page<UserResponse> getAllUsers(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> users = userRepository.findAll(pageRequest);

        List<User> filteredUsers = users.stream()
                .filter(user -> !user.getIsDeleted() || !user.getIsBlocked())
                .toList();

        return new PageImpl<>(filteredUsers, pageRequest, filteredUsers.size()).map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse getUserById(String alias) {
        User user = userRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));

        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        validateUserExists(userRequest);

        User user = userMapper.fromUserRequest(userRequest);
        setUserDetails(user, userRequest);
        setUserAuthorities(user, userRequest);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse updateUser(String alias, UserRequest userRequest) {
        User user = userRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", alias)));

        setUserDetails(user, userRequest);
        setUserAuthorities(user, userRequest);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(String alias) {
        User user = findUserByAlias(alias);
        userRepository.delete(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse disableUser(String alias) {
        return updateUserBlockedStatus(alias, true);
    }

    @Override
    @Transactional
    public UserResponse enableUser(String alias) {
        return updateUserBlockedStatus(alias, false);
    }

    @Override
    @Transactional
    public UserResponse isDeleted(String alias) {
        int affectedRows = userRepository.updateDeletedStatusById(alias, true);
        return getUserResponseIfAffected(alias, affectedRows);
    }



}
