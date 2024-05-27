package co.istad.lms.features.generation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenerationRequest(
        @NotBlank(message = "Alias cannot be null")
        String alias,

        @NotBlank(message = "Name cannot be null")
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,

        String description,

        @NotNull(message = "Start year cannot be null")
        Integer startYear,

        @NotNull(message = "End year cannot be null")
        Integer endYear
) {
}
