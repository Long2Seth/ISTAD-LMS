package co.istad.lms.features.password;


import co.istad.lms.features.password.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/password")
public class PasswordController {

    private final PasswordService passwordService;

    // Change password
    // @param request
    // @return ChangePasswordResponse

    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/change")
    public ChangePasswordResponse changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        return passwordService.changePassword(request);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @GetMapping("/view")
    public ResponsePassword viewPasswordByUsernameOrEmail(@Valid @RequestBody RequestPasswordByUsernameOrEmail request) {
        return passwordService.viewPasswordByUsernameOrEmail(request);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/reset")
    public ResetPasswordResponse resetPassword(@Valid @RequestBody RequestPasswordByUsernameOrEmail request) {
        return passwordService.resetPassword(request);
    }
}
