package co.istad.lms.features.authority.dto;


import lombok.Builder;

@Builder
public record AuthorityRequestToUser(
        String authorityName
) {
}
