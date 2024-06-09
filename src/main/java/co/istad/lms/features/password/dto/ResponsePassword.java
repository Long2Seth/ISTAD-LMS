package co.istad.lms.features.password.dto;

public record ResponsePassword (
        String email,
        String username,
        String password
) {
}
