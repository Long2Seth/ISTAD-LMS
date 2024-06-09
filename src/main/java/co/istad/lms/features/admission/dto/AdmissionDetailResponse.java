package co.istad.lms.features.admission.dto;

import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import java.time.LocalDate;

// DTO for detailed Admission response, potentially including related entities
public record AdmissionDetailResponse(
        String uuid,
        Integer status,
        String remark,
        LocalDate openDate,
        LocalDate endDate,

        String telegramLink,
        Boolean isDeleted
) {}
