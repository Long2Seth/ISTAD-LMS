package co.istad.lms.features.subject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SubjectRequest(

        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Subject name is required")
        @Size(max = 50, message = "Subject Name cannot be longer than 50 characters")
        String subjectName,

        String description,
        String subjectLogo,
        Integer credit,
        Integer duration,

        @NotNull(message = "Subject Alias is required")
        Set<String> studyProgramAlias

) {
}

