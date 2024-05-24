package co.istad.lms.features.authority.dto;


import lombok.Builder;

@Builder
public record AuthorityResponse(

        String uuid,
        String authorityName,
        String description
) {
}
