package co.istad.lms.features.staff.dto;


import co.istad.lms.features.user.dto.UserRequest;
import lombok.Builder;

@Builder
public record StaffRequest(
        String position,
        UserRequest userRequest
) {
}
