package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AdmissionUpdateStatusRequest(

        @NotBlank
        @Pattern(regexp = "[1-3]", message = "status must be 1, 2, or 3")
        Integer status
) {
}
