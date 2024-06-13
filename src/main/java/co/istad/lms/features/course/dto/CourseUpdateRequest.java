package co.istad.lms.features.course.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CourseUpdateRequest(

        String  courseStart,
        Integer status
) {
}
