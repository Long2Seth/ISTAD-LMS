package co.istad.lms.features.auth;


import co.istad.lms.domain.User;
import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthRequestResetPassword;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;
import co.istad.lms.features.user.UserRepository;
import co.istad.lms.security.CustomUserDetails;
import co.istad.lms.security.TokenGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.emailOrUsername());
            if (!userDetails.isAccountNonLocked() || !userDetails.isEnabled()) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        String.format("Invalid email or username and password . Please try again.")
                );

            }

            Authentication authentication = daoAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.emailOrUsername(),
                            request.password()
                    )
            );
            return tokenGenerator.generateTokens(authentication);

        } catch (AuthenticationException ex) {

            //Handle invalid credentials exception with password
            if (ex.getMessage().equalsIgnoreCase("Bad credentials")) {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        String.format("Invalid password. Please try again.")
                );
            } else {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        String.format("Invalid email or username. Please try again.")
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
    public void changePassword( @Valid AuthRequestResetPassword authRequestResetPassword) {


        // Get the current authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        // Get the user details from authentication
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
        }

        UserDetails userDetails = (UserDetails) principal;
        String email = userDetails.getUsername();
        System.out.println("email: " + email);

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

        // Save the updated user
        userRepository.save(user);


    }

    @Override
    public void logout(String token) {
        // TODO: Implement logout logic
    }
}
