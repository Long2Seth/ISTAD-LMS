package co.istad.lms.features.studyprogram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudyProgramUpdateRequest(

        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,


        @Size(max = 50, message = "Study program name cannot be longer than 50 characters")
        String studyProgramName,


        String description,

        String logo,

        Boolean isDeleted,

        String facultyAlias,

        String degreeAlias
) {
}
