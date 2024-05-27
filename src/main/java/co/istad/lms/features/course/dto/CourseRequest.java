package co.istad.lms.features.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank(message = "Alias cannot be blank")
        @Size(max = 100, message = "Alias must be less than 100 characters")
        String alias,

        @NotNull(message = "Status is required")
        Integer status,

        @NotNull(message = "isDeleted flag is required")
        Boolean isDeleted,

        @NotBlank(message = "Subject Alias cannot be blank")
        @Size(max = 100, message = "Subject Alias must be less than 100 characters")
        String subjectAlias,

        @NotBlank(message = "Instructor Alias cannot be blank")
        @Size(max = 100, message = "Instructor Alias must be less than 100 characters")
        String instructorAlias,

        @NotBlank(message = "Class Alias cannot be blank")
        @Size(max = 100, message = "Class Alias must be less than 100 characters")
        String classAlias
) {
}
