package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AcademicRequest(

        @NotNull(message = "UserRequest is required")
        @Valid
        UserRequest user

) {
}
