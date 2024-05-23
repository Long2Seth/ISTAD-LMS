package co.istad.lms.features.yearofstudy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record YearOfStudyRequest(
        @NotNull(message = "Year is required")
        @Positive(message = "Year must be a positive number")
        Integer year,

        @NotNull(message = "Semester is required")
        @Positive(message = "Semester must be a positive number")
        Integer semester,

        @NotBlank(message = "Study Program ID is required")
        String studyProgramAlias,

        @NotBlank(message = "Subject alias is required")
        String subjectAlias
) {
}
