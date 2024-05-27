package co.istad.lms.features.auth.dto;


import lombok.Builder;

@Builder
public record AuthRequest(
        String email,
        String password
) {
}
