package co.istad.lms.features.auth;


import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;
import co.istad.lms.security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenGenerator tokenGenerator;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse login(AuthRequest request) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.emailOrUsername());
            if (!userDetails.isAccountNonLocked() || !userDetails.isEnabled()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
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
                        HttpStatus.BAD_REQUEST,
                        String.format("Invalid password. Please try again.")
                );
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
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
    public void logout(String token) {
        // TODO: Implement logout logic
    }
}
