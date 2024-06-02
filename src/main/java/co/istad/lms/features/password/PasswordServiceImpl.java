package co.istad.lms.features.password;

import co.istad.lms.domain.User;
import co.istad.lms.features.password.dto.ChangePasswordRequest;
import co.istad.lms.features.password.dto.ChangePasswordResponse;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {

        // Check if the user is allowed to change password
        if (!userRepository.existsByIsChangePassword(false)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Another user is currently changing password"));
        }

        // Find the user by email or username
        User user = userRepository.findByEmailOrUsername(request.emailOrUsername(), request.emailOrUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with email or username %s not found", request.emailOrUsername())));

        // Verify the old password
        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is incorrect");
        }

        // Verify new password and confirm password match
        if (!request.newPassword().equals(request.confirmNewPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password and confirm password do not match");
        }

        // Update the user's password
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        user.setIsChangePassword(true);
        userRepository.save(user);

        return new ChangePasswordResponse("Password changed successfully");
    }
}
