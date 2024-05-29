package co.istad.lms.features.admin.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponseDetail;

public record AdminResponseDetail(
        String nameEn,
        String nameKh,
        String email,
        String username,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        JsonBirthPlace birthPlace,
        UserResponseDetail user
) {
}
