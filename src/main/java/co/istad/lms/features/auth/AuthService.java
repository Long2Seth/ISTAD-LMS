package co.istad.lms.features.auth;

import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;


/**
 * Business logic interface which contains to manage authentication
 *
 * @author Long Piseth
 * @since 1.0 (2024)
 */
public interface AuthService {


    /**
     * Login with username and password.
     *
     * @param request is the request object containing username and password
     * @return {@link AuthResponse}
     * @since 1.0 (2024)
     */
    AuthResponse login(AuthRequest request);


    /**
     * Refresh token.
     *
     * @param request is the request object containing refresh token
     * @return {@link AuthResponse}
     * @since 1.0 (2024)
     */
    AuthResponse refreshToken(RefreshTokenRequest request);


    /**
     * Logout.
     *
     * @param token is the token to logout
     * @since 1.0 (2024)
     */
    void logout(String token);
}
