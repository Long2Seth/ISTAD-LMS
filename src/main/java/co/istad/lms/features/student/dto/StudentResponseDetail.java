package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.UserResponse;

public record StudentResponseDetail(
        String uuid,
        UserResponse user
) {
}
