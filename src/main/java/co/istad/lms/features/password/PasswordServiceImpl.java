package co.istad.lms.features.password;

import co.istad.lms.domain.User;
import co.istad.lms.features.password.dto.*;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.mapper.UserMapper;
import jakarta.websocket.Decoder;
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


    @Override
    public ResponsePassword viewPasswordByUsernameOrEmail(RequestPasswordByUsernameOrEmail request) {

        // Find the user by email or username
        User user = userRepository.findByEmailOrUsername(request.usernameOrEmail(), request.usernameOrEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with email or username %s not found", request.usernameOrEmail())));

//        user.setPassword(Decoder(user.getPassword());

        return userMapper.toResponsePassword(user);
    }

    @Override
    public ResetPasswordResponse resetPassword(RequestPasswordByUsernameOrEmail request) {
        // Find the user by email or username
        User user = userRepository.findByEmailOrUsername(request.usernameOrEmail(), request.usernameOrEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with email or username %s not found", request.usernameOrEmail())));

        // Determine role and generate a new password accordingly
        String newPassword;
        Long userId = user.getId();
        // Generate a new password based on the user's role
        // if the user is a student, the password will be "ISTAD@STUDENT" + username
        if (userRepository.existsByUserIdAndStudent(userId)) {
            newPassword = "ISTAD@STUDENT" + user.getUsername();
        }

        //if the user is an instructor, the password will be "ISTAD@INSTRUCTOR" + username
        else if (userRepository.existsByUserIdAndInstructor(userId)) {
            newPassword = "ISTAD@INSTRUCTOR" + user.getUsername();
        }

        // if the user is a staff, the password will be "ISTAD@STAFF" + username
        else if (userRepository.existsByUserIdAndStaff(userId)) {
            newPassword = "ISTAD@STAFF" + user.getUsername();
        }

        // if the user is an academic, the password will be "ISTAD@ACADEMIC" + username
        else if (userRepository.existsByUserIdAndAcademic(userId)) {
            newPassword = "ISTAD@ACADEMIC" + user.getUsername();
        }

        // if the user is an admin, the password will be "ISTAD@ADMIN" + username
        else if (userRepository.existsByUserIdAndAdmin(userId)) {
            newPassword = "ISTAD@ADMIN" + user.getUsername();
        }

        // if the user has no associated role, throw an exception
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("User with email or username %s has no associated role", request.usernameOrEmail()));
        }

        // Update the user's password
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setIsChangePassword(false);
        userRepository.save(user);

        return new ResetPasswordResponse("Password reset successfully");
    }

}
