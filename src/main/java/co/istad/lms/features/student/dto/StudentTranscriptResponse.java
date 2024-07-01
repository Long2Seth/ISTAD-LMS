package co.istad.lms.features.student.dto;

import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;

import java.time.LocalDate;
import java.util.Set;

public record StudentTranscriptResponse(
        String uuid,
        String cardId,
        String nameEn,
        String gender,
        LocalDate dob,
        Integer status,

        Integer year,

        Double semester1Score,
        Double semester2Score,

        String grade,

        Double gpa,
        Double total


) {
}
