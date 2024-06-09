package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// DTO for updating an existing Admission
public record AdmissionUpdateRequest(

        String remark,
        LocalDate endDate,
        String telegramLink
) {}
