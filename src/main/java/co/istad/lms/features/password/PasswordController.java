package co.istad.lms.features.password;


import co.istad.lms.features.password.dto.ChangePasswordRequest;
import co.istad.lms.features.password.dto.ChangePasswordResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
