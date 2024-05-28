package co.istad.lms.features.staff.dto;


import co.istad.lms.features.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StaffRequest(
        String position,
        @NotNull(message = "User is required")
        @Valid
        UserRequest userRequest
) {
}
