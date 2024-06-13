package co.istad.lms.features.material.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MaterialRequest(

        @NotBlank(message = "alias is required")
        @Size(max = 100, message = "alias cannot be longer than 100 characters")
        String alias,
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "fileName URL is required")
        String fileName,
        @Size(max = 100, message = "contentType cannot be longer than 100 characters")
        String contentType,
        @Size(max = 20, message = "extension cannot be longer than 20 characters")
        String extension,
        @Positive(message = "size must be positive")
        Long size,
        String description,
        @NotBlank(message = "Subject alias is required")
        @Size(max = 100, message = "subjectAlias cannot be longer than 100 characters")
        String subjectAlias,
        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}


