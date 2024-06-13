package co.istad.lms.features.auth;

import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthRequestResetPassword;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;
import co.istad.lms.features.auth.dto.RequestPasswordByUsernameOrEmail;
import co.istad.lms.features.auth.dto.ResetPasswordResponse;
import co.istad.lms.features.password.dto.ResponsePassword;


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
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    AuthResponse login(AuthRequest request);


    /**
     * Refresh token.
     *
     * @param request is the request object containing refresh token
     * @return {@link AuthResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    AuthResponse refreshToken(RefreshTokenRequest request);


    /**
     *
     * @param authRequestResetPassword is the request object containing new password and confirm password
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void changePassword(AuthRequestResetPassword authRequestResetPassword);




    /**
     * Reset password.
     *
     * @param request the request
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void resetPassword(RequestPasswordByUsernameOrEmail request);

    /**
     * View password by username or email.
     *
     * @param request the request
     * @author Long Piseth
     * @return the response password by username or email
     */
    ResponsePassword viewPasswordByUsernameOrEmail(RequestPasswordByUsernameOrEmail request);

    /**
     * Logout.
     *
     * @param token is the token to logout
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void logout(String token);
}
