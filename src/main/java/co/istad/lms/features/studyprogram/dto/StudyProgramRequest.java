package co.istad.lms.features.studyprogram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public record StudyProgramRequest(
        @NotBlank(message = "Alias is required")
        @Size(max = 100, message = "Alias cannot be longer than 100 characters")
        String alias,

        @NotBlank(message = "Study program name is required")
        @Size(max = 255, message = "Study program name cannot be longer than 100 characters")
        String studyProgramName,

        String description,

        String logo,

        @NotBlank(message = "facultyAlias is require")
        @Size(max = 100, message = "facultyAlias can not be longer than 100 characters")
        String facultyAlias,

        @NotBlank(message = "degreeAlias is require")
        @Size(max = 100,message = "degreeAlias can not be longer than 100 characters")
        String degreeAlias,
        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}
