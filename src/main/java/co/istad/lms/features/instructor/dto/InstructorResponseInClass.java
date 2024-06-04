package co.istad.lms.features.instructor.dto;

public record InstructorResponseInClass(
        String uuid,
        String userUuid,
        String userName,
        String nameEn,
        String gender,
        String email
) {
}
