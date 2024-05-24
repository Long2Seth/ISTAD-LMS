package co.istad.lms.features.yearofstudy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record YearOfStudyUpdateRequest(

        @Positive(message = "Semester must be a positive number")
        Integer year,

        @Positive(message = "Semester must be a positive number")
        Integer semester,

        String studyProgramAlias

) {
}
