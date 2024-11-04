package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.features.course.dto.CourseResponse;

import java.util.Set;

public record YearOfStudyStudentAchievementResponse(
        int year,
        int semester,
        Set<CourseResponse> courses
) {
}
