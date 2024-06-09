package co.istad.lms.features.student.dto;

public record StudentResponseToUse(
        String uuid,
        String userUuid,
        String userName,
        String nameEn,
        String gender
) {
}
