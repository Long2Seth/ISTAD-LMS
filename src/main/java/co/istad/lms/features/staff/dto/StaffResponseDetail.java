package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.UserRequestDetail;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;

public record StaffResponseDetail(
        String uuid,
        String position,
        boolean status,
        UserResponseDetail user
) {
}
