package co.istad.lms.features.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank(message = "Alias cannot be blank")
        @Size(max = 100, message = "Alias must be less than 100 characters")
        String alias,

        @NotBlank(message = "Alias cannot be blank")
        @Size(max = 100, message = "Alias must be less than 100 characters")
        String title,

        @NotNull(message = "Status is required")
        Integer status,

        @NotBlank(message = "Subject Alias cannot be blank")
        @Size(max = 100, message = "Subject Alias must be less than 100 characters")
        String subjectAlias,

        @Size(max = 100, message = "Instructor Uuid must be less than 100 characters")
        String instructorUuid,

        @NotBlank(message = "Class Alias cannot be blank")
        @Size(max = 100, message = "Class Alias must be less than 100 characters")
        String classAlias
) {
}
