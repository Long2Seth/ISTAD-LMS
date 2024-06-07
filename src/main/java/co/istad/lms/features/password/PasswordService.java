package co.istad.lms.features.password;

import co.istad.lms.features.password.dto.*;


/**
 * Business logic interface which contains to manage password
 *
 * @author Long Piseth
 * @since 1.0 (2024)
 */
public interface PasswordService {


    /**
     * Change password.
     *
     * @param request the request
     * @return the change password response
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    ChangePasswordResponse changePassword(ChangePasswordRequest request);


    /**
     * View password by username or email.
     *
     * @param request the request
     * @return the view password by username or email response
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    ResponsePassword viewPasswordByUsernameOrEmail( RequestPasswordByUsernameOrEmail request );



    /**
     * Reset password.
     *
     * @param request the request
     * @return the reset password response
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    ResetPasswordResponse resetPassword(RequestPasswordByUsernameOrEmail request);
}
