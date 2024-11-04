package co.istad.lms.features.score.dto;

import co.istad.lms.features.course.dto.CourseScoreResponse;
import co.istad.lms.features.student.dto.StudentAssessmentResponse;
import co.istad.lms.features.student.dto.StudentResponse;

public record ScoreEachSemesterResponse(
        String uuid,
        Double activityScore,
        Double attendanceScore,
        Double midtermExamScore,
        Double finalExamScore,
        Double miniProjectScore,
        Double assignmentScore,
        String classCode,
        String grade,
        Double total,
        CourseScoreResponse course,
        StudentAssessmentResponse student,
        Boolean isDeleted
) {
}
