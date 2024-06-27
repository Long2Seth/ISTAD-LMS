package co.istad.lms.features.student.dto;

import java.time.LocalDate;

public record StudentTranscriptResponse(
        String uuid,
        String cardId,
        String nameEn,
        String gender,
        LocalDate dob,
        Integer status

) {
}
