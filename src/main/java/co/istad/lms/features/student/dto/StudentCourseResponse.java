package co.istad.lms.features.student.dto;

import co.istad.lms.features.course.dto.CourseWithUsersResponse;

import java.util.Set;

public record StudentCourseResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String gender,
        Set<CourseWithUsersResponse> courses
) {
}
