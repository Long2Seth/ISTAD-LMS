package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// DTO for updating an existing Admission
public record AdmissionUpdateRequest(

        Integer status,
        String remark,
        LocalDate endDate
) {}
