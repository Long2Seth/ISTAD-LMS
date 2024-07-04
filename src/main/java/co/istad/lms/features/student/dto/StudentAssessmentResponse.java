package co.istad.lms.features.student.dto;

public record StudentAssessmentResponse(
        String uuid,

        String nameEn,
        String cardId,

        String gender,

        String dob

) {
}
