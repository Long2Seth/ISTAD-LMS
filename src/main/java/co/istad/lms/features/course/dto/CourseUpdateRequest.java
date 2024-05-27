package co.istad.lms.features.course.dto;

import jakarta.validation.constraints.Size;

public record CourseUpdateRequest(

        @Size(max = 100, message = "Alias must be less than 100 characters")
        String alias,


        Integer status,


        Boolean isDeleted,


        @Size(max = 100, message = "Subject Alias must be less than 100 characters")
        String subjectAlias,


        @Size(max = 100, message = "Instructor Alias must be less than 100 characters")
        String instructorAlias,


        @Size(max = 100, message = "Class Alias must be less than 100 characters")
        String classAlias
) {
}
