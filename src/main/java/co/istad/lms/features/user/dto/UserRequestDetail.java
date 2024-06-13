package co.istad.lms.features.user.dto;

import co.istad.lms.features.authority.dto.AuthorityRequestToUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserRequestDetail(
        String nameEn,
        String nameKh,
        String username,
        String gender,
        LocalDate dob,
        String email,
        String profileImage,
        String phoneNumber,
        String currentAddress,
        JsonBirthPlace birthPlace

) {
}
