package co.istad.lms.features.auth.dto;

import jakarta.validation.constraints.Pattern;

public record AuthRequestResetPassword (

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{8,}$")
        String newPassword,

        String confirmPassword
) {
}