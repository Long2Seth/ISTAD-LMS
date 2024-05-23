package co.istad.lms.features.auth;

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
@RequestMapping("/api/v1/auth")
@SecurityRequirements(value = {})
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "User login",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "email": "john.doe@example.com",
                                                    "password": "password123"
                                                }
                                            """
                            )
                    )
            )
    )
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RefreshTokenRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "refreshToken": "sample_refresh_token"
                                                }
                                            """
                            )
                    )
            )
    )
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                                {
                                                    "alias": "sophea",
                                                    "nameEn": "sophea",
                                                    "nameKh": "សុភា",
                                                    "username": "sophea",
                                                    "gender": "Male",
                                                    "email": "sophea@example.com",
                                                    "password": "password123",
                                                    "profileImage": "http://example.com/profile.jpg",
                                                    "phoneNumber": "+123456789",
                                                    "cityOrProvince": "Phnom Penh",
                                                    "khanOrDistrict": "Chamkar Mon",
                                                    "sangkatOrCommune": "Tonle Bassac",
                                                    "street": "123",
                                                    "birthPlace": {
                                                        "cityOrProvince": "string",
                                                        "khanOrDistrict": "string",
                                                        "sangkatOrCommune": "string",
                                                        "villageOrPhum": "string",
                                                        "street": "string",
                                                        "houseNumber": "string"
                                                    },
                                                    "authorities": [
                                                        {
                                                            "authorityName": "user:write"
                                                        }
                                                    ]
                                                }
                                            """
                            )
                    )
            )
    )
    public UserResponse registerUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
