package co.istad.lms.features.admission.dto;

import java.time.LocalDate;

// DTO for a basic response with Admission details
public record AdmissionResponse(
        String uuid,
        Integer status
) {}
