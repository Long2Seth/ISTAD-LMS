package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserRequestDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StaffRequestDetail(
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
