package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(

        @NotNull(message = "User request is mandatory")
        UserRequest userRequest
) {
}
