package co.istad.lms.features.degree.dto;

import jakarta.validation.constraints.Size;

public record DegreeUpdateRequest(
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @Size(max = 50, message = "Level cannot be longer than 50 characters")
        String level,

        String description
) {
}
