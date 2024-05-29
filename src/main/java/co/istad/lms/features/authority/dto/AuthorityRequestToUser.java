package co.istad.lms.features.authority.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthorityRequestToUser(

        @NotNull
        String authorityName
) {
}
