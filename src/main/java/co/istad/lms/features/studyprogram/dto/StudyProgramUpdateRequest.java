package co.istad.lms.features.studyprogram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record StudyProgramUpdateRequest(

        @Size(max = 100, message = "Alias cannot be longer than 100 characters")
        String alias,


        @Size(max = 255, message = "Study program name cannot be longer than 255 characters")
        String studyProgramName,

        String description,

        String logo


) {
}
