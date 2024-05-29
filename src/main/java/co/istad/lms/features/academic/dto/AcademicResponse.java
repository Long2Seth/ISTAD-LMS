package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;

public record AcademicResponse(
        String uuid,
        boolean status ,
        UserResponse user
) {
}
