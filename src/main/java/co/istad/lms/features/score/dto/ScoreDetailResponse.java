package co.istad.lms.features.score.dto;

import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.student.dto.StudentResponse;

public record ScoreDetailResponse(
        String uuid,
        Integer semester,
        Double activityScore,
        Double attendanceScore,
        Double midtermExamScore,
        Double finalExamScore,
        Double miniProjectScore,
        Double assignmentScore,
        StudentResponse student,
        CourseResponse course,
        Boolean isDeleted
) {
}
