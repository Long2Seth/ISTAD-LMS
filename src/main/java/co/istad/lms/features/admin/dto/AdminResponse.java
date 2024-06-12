package co.istad.lms.features.admin.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserResponse;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record AdminResponse(
        String uuid,

        String nameEn,

        String nameKh,

        String username,

        String gender,

        LocalDate dob,

        String email,

        String profileImage,

        String phoneNumber
) {
}
