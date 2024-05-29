package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record InstructorRequest(

        @NotNull(message = "UserRequest is required")
        @Valid
        UserRequest userRequest

) {
}
