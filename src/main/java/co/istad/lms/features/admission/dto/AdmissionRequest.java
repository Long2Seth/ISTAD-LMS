package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// DTO for creating a new Admission
public record AdmissionRequest(
        @NotNull(message = "status is require")
        Integer status,
        String remark,
        @NotNull(message = "openDate is require")
        LocalDate openDate,
        LocalDate endDate,

        String telegramLink
) {}
