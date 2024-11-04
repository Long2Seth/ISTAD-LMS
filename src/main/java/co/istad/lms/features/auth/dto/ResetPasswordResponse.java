package co.istad.lms.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordResponse(
        String message
) {
}
