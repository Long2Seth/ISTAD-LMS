package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.features.validate.ValidInteger;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record YearOfStudyRequest(
        @NotNull(message = "Year is required")
//        @Positive(message = "Year must be a positive number")
//        @ValidInteger(field = "semester",message = " must be integer")
        Integer year,

        @NotNull(message = "Semester is required")
        @Positive(message = "Semester must be a positive number")
        @ValidInteger(field = "sfs",message = " must be integer")
        Integer semester,

        @NotBlank(message = "Study Program ID is required")
        String studyProgramAlias,
        @NotNull(message = "isDraft is required")
        Boolean isDraft


) {
}
