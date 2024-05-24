package co.istad.lms.features.subject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

        @NotBlank(message = "Subject Alias is required")
        String subjectAlias

) {
}

