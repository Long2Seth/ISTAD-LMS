package co.istad.lms.features.authority.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthorityRequestToUser(
        @NotNull(message = "Authority name is required")
        String authorityName
) {
}
