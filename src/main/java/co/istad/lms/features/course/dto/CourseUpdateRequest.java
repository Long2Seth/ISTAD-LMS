package co.istad.lms.features.course.dto;

import jakarta.validation.constraints.Size;

public record CourseUpdateRequest(

        @Size(max = 100, message = "Alias must be less than 100 characters")
        String alias,

        Integer status
) {
}
