package co.istad.lms.features.auth;


import co.istad.lms.base.BasedResponse;
import co.istad.lms.features.auth.dto.AuthRequest;
import co.istad.lms.features.auth.dto.AuthResponse;
import co.istad.lms.features.auth.dto.RefreshTokenRequest;
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
@RequestMapping("api/v1/auth")
@SecurityRequirements(value = {})
public class AuthRestController {

    private final AuthServiceImpl authService;
    private final UserService userService;

    @PostMapping("/login")
    public BasedResponse<AuthResponse> login(@RequestBody AuthRequest request){
        return BasedResponse.<AuthResponse>ok()
                .setPayload(authService.login(request));
    }

    @PostMapping("/refresh")
    public BasedResponse<AuthResponse> refresh(@RequestBody RefreshTokenRequest request){
        return BasedResponse.<AuthResponse>ok()
                .setPayload(authService.refreshToken(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register new user"
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = UserRequest.class)
            )
    )
    )
    public BasedResponse<UserResponse> registerUser(
            @Valid @RequestBody UserRequest userRequest) {
        return BasedResponse.<UserResponse>createSuccess()
                .setPayload(userService.createUser(userRequest));
    }




}
