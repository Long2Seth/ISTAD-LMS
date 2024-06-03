package co.istad.lms.features.password.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestPasswordByUsernameOrEmail(

        @NotBlank( message = "Username or email is required")
        String usernameOrEmail
) {
}
