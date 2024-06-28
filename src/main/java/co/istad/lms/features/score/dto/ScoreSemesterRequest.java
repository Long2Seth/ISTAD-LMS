package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.*;

public record ScoreSemesterRequest(
        @NotBlank(message = "studyProgramAlias is require")
        String studyProgramAlias,

        @NotBlank(message = "generationUuid is require")
        String generationAlias,

        @NotNull(message = "year is require")
        @Positive(message = "year is positive")
        @Max(value = 4, message = "max of year is 4")
        Integer year,

        @Positive(message = "semester is positive")
        @Max(value = 2, message = "max of year is 2")
        @NotNull(message = "semester is require")
        Integer semester
) {
}
