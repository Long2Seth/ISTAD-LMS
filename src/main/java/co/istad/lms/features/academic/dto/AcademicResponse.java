package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;

public record AcademicResponse(
        String uuid,

        String nameEn,

        String nameKh,

        String username,

        String gender,

        LocalDate dob,

        String email,

        String profileImage,

        String phoneNumber

) {
}
