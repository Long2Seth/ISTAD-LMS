package co.istad.lms.features.user.dto;

import co.istad.lms.features.authority.dto.AuthorityResponseToUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserResponseDetail(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String gender,
        LocalDate dob,
        String email,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        JsonBirthPlace birthPlace,
        Set<AuthorityResponseToUser> authorities
) {
}
