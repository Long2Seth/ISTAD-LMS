package co.istad.lms.features.user.dto;

import co.istad.lms.features.authority.dto.AuthorityRequestToUser;

import java.time.LocalDate;
import java.util.Set;

public record UserUpdateRequest(
        String nameEn,
        String nameKh,
        String username,
        String gender,
        LocalDate dob,
        String email,
        String password,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        JsonBirthPlace birthPlace,
        Set<AuthorityRequestToUser> authorities
) {
}
