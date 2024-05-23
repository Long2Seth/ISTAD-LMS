package co.istad.lms.features.studyprogram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudyProgramRequest(
        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Study program name is required")
        @Size(max = 50, message = "Study program name cannot be longer than 50 characters")
        String studyProgramName,

        String description,

        String logo,

        @NotNull(message = "isDeleted is required")
        Boolean isDeleted,

        @NotBlank
        String facultyAlias
) {
}
