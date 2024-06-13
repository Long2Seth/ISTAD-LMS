package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// DTO for updating an existing Admission
public record AdmissionUpdateRequest(

        String remark,
        String openDate,
        String  endDate,
        @Size(max = 100,message = "telegram link must be shorter than 100")
        String telegramLink
) {}
