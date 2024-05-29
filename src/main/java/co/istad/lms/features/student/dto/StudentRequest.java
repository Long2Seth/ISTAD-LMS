package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(

        @NotNull(message = "UserRequest is required")
        @Valid
        UserRequest user
) {
}
