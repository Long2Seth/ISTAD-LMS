package co.istad.lms.features.password;


import co.istad.lms.features.password.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
public class PasswordController {

    private final PasswordService passwordService;

    // Change password
    // @param request
    // @return ChangePasswordResponse
    @PatchMapping("/change")
    public ChangePasswordResponse changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        return passwordService.changePassword(request);
    }

    @GetMapping("/view")
    public ResponsePassword viewPasswordByUsernameOrEmail(@Valid @RequestBody RequestPasswordByUsernameOrEmail request) {
        return passwordService.viewPasswordByUsernameOrEmail(request);
    }

    @PatchMapping("/reset")
    public ResetPasswordResponse resetPassword(@Valid @RequestBody RequestPasswordByUsernameOrEmail request) {
        return passwordService.resetPassword(request);
    }
}
