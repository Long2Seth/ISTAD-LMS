package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserRequestDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record StaffRequestDetail(
        String position,
        UserRequestDetail user
) {
}
