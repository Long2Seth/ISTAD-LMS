package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequestDetail;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;

import java.time.LocalDate;

public record StaffResponseDetail(
        String uuid,
        String position,
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
