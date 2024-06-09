package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

// DTO for creating a new Admission
public record AdmissionRequest(
        @NotNull(message = "status is require")
        @Min(value = 1, message = "Status must be 1, 2, or 3")
        @Max(value = 3, message = "Status must be 1, 2, or 3")
        Integer status,

        String remark,
        @NotNull(message = "openDate is require")
        LocalDate openDate,
        LocalDate endDate,

        String telegramLink
) {}
