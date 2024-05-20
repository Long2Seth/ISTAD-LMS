package co.istad.lms.features.authority.dto;


import lombok.Builder;

@Builder
public record AuthorityResponse(
        Long id,
        String authorityName,
        String description
) {
}
