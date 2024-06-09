package co.istad.lms.features.material.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MaterialRequest(

        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,
        @NotBlank(message = "Title is required")
        @Size(max = 50, message = "Title cannot be longer than 50 characters")
        String title,
        @NotBlank(message = "File URL is required")
        String fileName,
        String contentType,
        String extension,
        Long size,
        String description,
        @NotBlank(message = "Subject alias is required")
        String subjectAlias,
        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}


