package co.istad.lms.features.degree.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DegreeCreateRequest(
        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Level is required")
        @Size(max = 50, message = "Level cannot be longer than 50 characters")
        String level,

        String description
) {
}
