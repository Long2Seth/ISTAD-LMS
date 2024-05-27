package co.istad.lms.features.student.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;

public record StudentResponse(
        String uuid,
        UserResponse userResponse
) {
}
