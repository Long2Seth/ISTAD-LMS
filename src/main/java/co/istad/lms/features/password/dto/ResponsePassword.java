package co.istad.lms.features.password.dto;

import java.time.LocalDate;

public record ResponsePassword (
        String email,
        String username,
        LocalDate dob,
        String rawPassword
) {
}
