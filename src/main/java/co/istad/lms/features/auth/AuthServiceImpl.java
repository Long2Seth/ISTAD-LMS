package co.istad.lms.features.auth;


import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;
import co.istad.lms.security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenGenerator tokenGenerator;

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = daoAuthenticationProvider
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );
        return tokenGenerator.generateTokens(authentication);
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Authentication authentication = jwtAuthenticationProvider
                .authenticate(
                        new BearerTokenAuthenticationToken(request.refreshToken())
                );
        return tokenGenerator.generateTokens(authentication);

    }
}
