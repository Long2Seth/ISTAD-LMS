package co.istad.lms.features.student.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record StudentScoreResponse(
        String cardId,
        String uuid,
        String nameEn,
        String gender,
        Integer status
) {
}
