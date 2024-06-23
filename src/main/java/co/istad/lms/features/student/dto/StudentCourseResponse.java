package co.istad.lms.features.student.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.course.dto.CourseStudentResponse;
import co.istad.lms.features.user.dto.JsonBirthPlace;

import java.time.LocalDate;
import java.util.Set;

public record StudentCourseResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String gender,
        Set<CourseStudentResponse> courses
) {
}
