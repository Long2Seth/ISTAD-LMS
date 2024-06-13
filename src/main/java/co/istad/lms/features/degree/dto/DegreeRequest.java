package co.istad.lms.features.degree.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DegreeRequest(
        @NotBlank(message = "Alias is required")
        @Size(max = 100, message = "Alias can not be longer than 100 characters")
        String alias,

        @NotBlank(message = "Level is required")
        @Size(max = 100, message = "Level can not be longer than 100 characters")
        String level,

        String description,

        @NotNull(message = "isDraft is required")
        Boolean isDraft
) {
}
