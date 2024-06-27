package co.istad.lms.features.score.dto;

import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.student.dto.StudentTranscriptResponse;

import java.nio.DoubleBuffer;
import java.util.Set;

public record ScoreSemesterResponse(

        String uuid,
        String classCode,
        Double total,
        Double gpa,
        StudentTranscriptResponse student,
        Set<CourseSemesterScoreResponse> course
) {
}
