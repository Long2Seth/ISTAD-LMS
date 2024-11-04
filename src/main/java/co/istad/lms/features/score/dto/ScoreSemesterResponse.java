package co.istad.lms.features.score.dto;

import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.student.dto.StudentTranscriptResponse;
import lombok.Builder;

import java.nio.DoubleBuffer;
import java.util.Set;

@Builder
public record ScoreSemesterResponse(

        String classCode,
        Double total,
        Double gpa,
        StudentTranscriptResponse student,
        Set<CourseSemesterScoreResponse> courses
) {
}
