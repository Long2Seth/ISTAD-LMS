package co.istad.lms.features.yearofstudy.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

public record YearOfStudyRequest(
        @NotNull(message = "Year is required")
        @Positive(message = "Year must be a positive number")
        Integer year,

        @NotNull(message = "Semester is required")
        @Positive(message = "Semester must be a positive number")
                @Max(value = 2,message = "Semester is must between 1-2")
        Integer semester,

        @NotBlank(message = "Study Program ID is required")
        @Size(max = 100,message = "studyProgramAlias cannot be longer than 100 characters")
        String studyProgramAlias,
        @NotNull(message = "isDraft is required")
        Boolean isDraft


) {
}
