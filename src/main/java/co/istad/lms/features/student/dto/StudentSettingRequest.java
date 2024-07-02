package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

public record StudentSettingRequest(

        String gender,
        String profileImage,
        String phoneNumber,
        String bio,
        String currentAddress,
        String birthPlace
) {
}
