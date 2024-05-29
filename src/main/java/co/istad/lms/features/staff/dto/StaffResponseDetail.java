package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.UserRequestDetail;
import co.istad.lms.features.user.dto.UserResponse;

public record StaffResponseDetail(
        String uuid,
        String position,
        UserResponse user
) {
}
