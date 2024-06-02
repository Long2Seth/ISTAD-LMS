package co.istad.lms.features.password;

import co.istad.lms.features.password.dto.ChangePasswordRequest;
import co.istad.lms.features.password.dto.ChangePasswordResponse;

public interface PasswordService {

    ChangePasswordResponse changePassword(ChangePasswordRequest request);
}
