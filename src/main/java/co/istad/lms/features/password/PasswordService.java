package co.istad.lms.features.password;

import co.istad.lms.features.password.dto.*;

public interface PasswordService {

    ChangePasswordResponse changePassword(ChangePasswordRequest request);
    ResponsePassword viewPasswordByUsernameOrEmail( RequestPasswordByUsernameOrEmail request );

    ResetPasswordResponse resetPassword(RequestPasswordByUsernameOrEmail request);
}
