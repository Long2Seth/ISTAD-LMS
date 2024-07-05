package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

public record StudentSetting(

        String gender,
        String profileImage,
        String phoneNumber,
        String familyPhoneNumber,
        String guardianRelationShip,
        String biography,
        String currentAddress,
        String birthPlace
) {
}
