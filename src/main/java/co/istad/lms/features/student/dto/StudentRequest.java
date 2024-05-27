package co.istad.lms.features.student.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserRequest;

public record StudentRequest(
        UserRequest userRequest
) {
}
