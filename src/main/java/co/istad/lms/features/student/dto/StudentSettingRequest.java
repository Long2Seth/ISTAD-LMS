package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

public record StudentSettingRequest(
        String nameEn,
        String nameKh,
        String gender,
        String dob,
        String email,
        String profileImage,
        String phoneNumber,
        String bio,
        String currentAddress,
        String birthPlace
) {
}
