package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.*;

public record AdmissionUpdateStatusRequest(

        @NotNull
        @Min(value = 1, message = "Status must be 1, 2, or 3")
        @Max(value = 3, message = "Status must be 1, 2, or 3")
        Integer status
) {
}
