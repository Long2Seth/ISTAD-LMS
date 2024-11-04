package co.istad.lms.features.auth;

import co.istad.lms.features.auth.dto.*;
import co.istad.lms.features.password.dto.ResponsePassword;
import co.istad.lms.features.user.UserService;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@SecurityRequirements(value = {})
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PutMapping("/passwords")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword (@Valid @RequestBody AuthRequestResetPassword authRequestResetPassword) {
        authService.changePassword(authRequestResetPassword);
    }


    @PutMapping("/passwords/reset")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@Valid @RequestBody RequestPasswordByUsernameOrEmail authRequestResetPassword) {
          authService.resetPassword(authRequestResetPassword);
    }

    @GetMapping("/passwords")
    @ResponseStatus(HttpStatus.OK)
    public ResponsePassword viewPasswordByUsernameOrEmail(@RequestBody RequestPasswordByUsernameOrEmail authRequestResetPassword) {
        return authService.viewPasswordByUsernameOrEmail(authRequestResetPassword);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
