package co.istad.lms.features.auth;


import co.istad.lms.domain.User;
import co.istad.lms.features.auth.dto.*;
import co.istad.lms.features.password.dto.ResponsePassword;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.features.user.UserService;
import co.istad.lms.mapper.UserMapper;
import co.istad.lms.security.CustomUserDetails;
import co.istad.lms.security.TokenGenerator;
import co.istad.lms.util.OtpUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {




    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;





    @Override
    public AuthResponse login(AuthRequest request) {



        try {
            // Load user details using the provided email or username
            User user = userRepository.findByEmailOrUsername(request.emailOrUsername(), request.emailOrUsername())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("User with email or username %s not found", request.emailOrUsername())
                    ));

            // Create a custom UserDetails object
            CustomUserDetails customUserDetails = new CustomUserDetails(user, user.getRawPassword());

            // Check if the account is locked or disabled
            if (!customUserDetails.isAccountNonLocked() || !customUserDetails.isEnabled()) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or username and password. Please try again."
                );
            }

            // Check if password is null, if so, assign rawPassword to password
            if (customUserDetails.getPassword() == null) {
                user.setPassword(passwordEncoder.encode(user.getRawPassword()));
                user.setRawPassword(null);
                 // Save the user with the updated password
                userRepository.save(user);
            }

            // Authenticate using the provided email or username and password
            Authentication authentication = daoAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.emailOrUsername(),
                            request.password()
                    )
            );

            // Generate tokens upon successful authentication
            return tokenGenerator.generateTokens(authentication);

        } catch (AuthenticationException ex) {
            // Handle invalid credentials exception
            if ("Bad credentials".equalsIgnoreCase(ex.getMessage())) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid password. Please try again."
                );
            } else {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or username. Please try again."
                );
            }
        }
    }





    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Authentication authentication = jwtAuthenticationProvider.authenticate(
                new BearerTokenAuthenticationToken(request.refreshToken())
        );
        return tokenGenerator.generateTokens(authentication);
    }





    @Override
    public void changePassword(@Valid AuthRequestResetPassword authRequestResetPassword) {


        // Get the current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    String.format("User is not authenticated")
            );
        }


        // Get the user details from authentication
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }


        // Get the email from the user details
        UserDetails userDetails = (UserDetails) principal;
        String email = userDetails.getUsername();


        // Fetch the user from the repository
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with username %s not found", email)
                ));


        // Validate the new password and confirm password
        if (!authRequestResetPassword.newPassword().equals(authRequestResetPassword.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Confirm password must match the new password.")
            );
        }


        // Set the new password
        user.setPassword(passwordEncoder.encode(authRequestResetPassword.newPassword()));
        user.setRawPassword(null);
        user.setIsChangePassword(true);


        // Save the updated user
        userRepository.save(user);


    }




    @Override
    public void resetPassword(RequestPasswordByUsernameOrEmail request) {
        // Fetch the user by email or username
        User user = userRepository.findByEmailOrUsername(request.usernameOrEmail(), request.usernameOrEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with email or username %s not found", request.usernameOrEmail())
                ));

        // Update the user entity with the new password
        String rawPassword = userService.generateStrongPassword(10);
        try {
            //generate key for encrypt
            SecretKey key = OtpUtil.generateKey();

            //encrypt password
            String encryptedPassword = OtpUtil.encryptOTP(rawPassword, key);

            //set raw password with encrypt password
            user.setRawPassword(encryptedPassword);
            user.setPassword(null);

        } catch (Exception e) {
            throw new RuntimeException("Error generating or encrypting password", e);
        }

        userRepository.save(user);

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
    public void logout(String token) {
        // TODO: Implement logout logic
    }
}
