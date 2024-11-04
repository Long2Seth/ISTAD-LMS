package co.istad.lms.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(

        String accessToken,
        String refreshToken
) {
}
