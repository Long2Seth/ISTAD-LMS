package co.istad.lms.features.course.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CourseRequest(

        @NotBlank(message = "Alias cannot be blank")
        String title,

        @NotNull(message = "Status is required")
        @Max(value = 3, message = "max value is 3")
        @Min(value = 1, message = "min value is 1")
        Integer status,

        String  courseStart,

        @NotNull(message = "isStarted is require")
        Boolean isStarted,

        @NotBlank(message = "Subject Alias cannot be blank")
        @Size(max = 100, message = "SubjectAlias can not be longer than 100 characters")
        String subjectAlias,

        @Size(max = 100, message = "InstructorUuid cannot be longer than 100 characters")
        String instructorUuid,

        @NotBlank(message = "Class uuid cannot be blank")
        @Size(max = 100, message = "classUuid cannot be longer than 100 characters")
        String classUuid
) {
}
