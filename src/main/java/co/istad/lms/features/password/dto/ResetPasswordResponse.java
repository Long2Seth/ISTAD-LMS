package co.istad.lms.features.password.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordResponse(
        String message
) {
}
