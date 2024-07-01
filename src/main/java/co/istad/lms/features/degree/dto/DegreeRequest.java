package co.istad.lms.features.degree.dto;

import jakarta.validation.constraints.*;

public record DegreeRequest(
        @NotBlank(message = "Alias is required")
        @Size(max = 100, message = "Alias can not be longer than 100 characters")
        String alias,

        @NotBlank(message = "Level is required")
        @Size(max = 100, message = "Level can not be longer than 100 characters")
        String level,

        @NotBlank(message = "numberOfYear is require")
        @Positive(message = "number is year is positive ")
        Integer numberOfYear,

        String description,

        @NotNull(message = "isDraft is required")
        Boolean isDraft
) {
}
