package co.istad.lms.features.user;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.User;
import co.istad.lms.features.authority.AuthorityRepository;
import co.istad.lms.features.file.FileMetaDataRepository;
import co.istad.lms.features.media.MediaService;
import co.istad.lms.features.user.dto.*;
import co.istad.lms.mapper.UserMapper;
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

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final MediaService mediaService;
    private final FileMetaDataRepository fileMetaDataRepository;



    @Override
    public String generateStrongPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        SecureRandom random = new SecureRandom();
        List<Character> passwordChars = new ArrayList<>();

        // Add at least one character of each required type
        passwordChars.add((char) (random.nextInt(26) + 'A')); // Uppercase letter
        passwordChars.add((char) (random.nextInt(26) + 'a')); // Lowercase letter
        passwordChars.add((char) (random.nextInt(10) + '0')); // Digit
        passwordChars.add("@$!%*?&".charAt(random.nextInt("@$!%*?&".length()))); // Special character

        // Fill the rest of the password length with random characters from the allowed set
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&";
        for (int i = 4; i < length; i++) {
            passwordChars.add(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }

        // Shuffle the characters to ensure randomness
        Collections.shuffle(passwordChars, random);

        // Convert the list of characters to a string
        return passwordChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }




    @Override
    public Page<UserResponse> getAllUsers(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<User> users = userRepository.findAll(pageRequest);

        users.forEach(user -> {
            if(user.getProfileImage() != null && !user.getProfileImage().trim().isEmpty()) {
                user.setProfileImage(mediaService.getUrl(user.getProfileImage()));
            }
        });

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

        users.forEach(user -> {
            if(user.getProfileImage() != null && !user.getProfileImage().trim().isEmpty()) {
                user.setProfileImage(mediaService.getUrl(user.getProfileImage()));
            }
        });

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

        users.forEach(user -> {
            if(user.getProfileImage() != null && !user.getProfileImage().trim().isEmpty()) {
                user.setProfileImage(mediaService.getUrl(user.getProfileImage()));
            }
        });

        return users.map(userMapper::toUserResponse);
    }


    @Override
    public UserResponse getUserById(String uuid) {

        // Check if user exists by email if not throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        if(user.getProfileImage() != null && !user.getProfileImage().trim().isEmpty()) {
            user.setProfileImage(mediaService.getUrl(user.getProfileImage()));
        }

        return userMapper.toUserResponse(user);

    }

    @Override
    public UserResponseDetail getUserDetailById(String uuid) {

        // Check if user exists by email if not throw exception
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with alias = %s was not found.", uuid)));

        user.setProfileImage(mediaService.getUrl(user.getProfileImage()));

        return userMapper.toUserResponseDetail(user);

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        // Validate if user exists by email or username
        if (userRepository.existsByEmailOrUsername(userRequest.email(), userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User email = %s has already been existed!", userRequest.email()));
        }

        if (userRequest.profileImage() != null && !userRequest.profileImage().trim().isEmpty() && !fileMetaDataRepository.existsByFileName(userRequest.profileImage())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("File with name = %s not found!", userRequest.profileImage()));
        }


        // Map user request to user
        User user = userMapper.fromUserRequest(userRequest);
        // Set user uuid
        user.setUuid(UUID.randomUUID().toString());

        user.setRawPassword(generateStrongPassword(10));
        user.setPassword(passwordEncoder.encode(user.getRawPassword()));
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
            if (foundAuthorities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Authority with name = %s not found!", authorityName));
            }
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
