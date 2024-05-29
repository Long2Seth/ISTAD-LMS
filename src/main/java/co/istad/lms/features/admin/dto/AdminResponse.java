package co.istad.lms.features.admin.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;
import java.util.List;

public record AdminResponse(
        String uuid,
        boolean status ,
        UserResponse user
) {
}
