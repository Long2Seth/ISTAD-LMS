package co.istad.lms.features.generation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GenerationRequest(
        @NotBlank(message = "alias is require")
        String alias,

        @NotBlank(message = "name is require")
        @Size(max = 100, message = "name can not be longer than 100 characters")
        String name,

        String description,

        @NotNull(message = "Start year cannot be null")
        @Positive(message = "startYear must be positive")
        Integer startYear,

        @NotNull(message = "End year cannot be null")
        @Positive(message = "endYear must be positive")
        Integer endYear,
        @NotNull(message = "isDraft is required")
        Boolean isDraft
) {
}
