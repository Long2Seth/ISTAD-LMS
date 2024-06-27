package co.istad.lms.features.transcript.dto;

public record TranscriptRequest(

        String studentUuid,
        String classCode,
        String yearOfStudyUuid

) {
}
