package co.istad.lms.features.transcript.dto;

import co.istad.lms.features.student.dto.StudentTranscriptResponse;

public record TranscriptResponse(
        String uuid,
        StudentTranscriptResponse student,
        String classCode,
        Integer year,
        Double semester1,
        Double semester2,
        Double gpa,
        Double total
) {
}
