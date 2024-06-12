package co.istad.lms.features.staff.dto;


import co.istad.lms.features.user.dto.UserResponse;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StaffResponse(

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
