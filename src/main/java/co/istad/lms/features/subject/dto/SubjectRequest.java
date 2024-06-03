package co.istad.lms.features.subject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SubjectRequest(

        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Subject title is required")
        @Size(max = 50, message = "Subject Name cannot be longer than 50 characters")
        String title,

        String description,
        String subjectLogo,

        @NotNull(message = "credit is require")
        Integer credit,

        @NotNull(message = "duration is require")
        Integer duration,

        String curriculum,

        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}

