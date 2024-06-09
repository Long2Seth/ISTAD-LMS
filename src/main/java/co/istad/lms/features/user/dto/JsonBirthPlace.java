package co.istad.lms.features.user.dto;

import lombok.Builder;

@Builder
public record JsonBirthPlace(
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String villageOrPhum,
        String street,
        String houseNumber
) {
}
