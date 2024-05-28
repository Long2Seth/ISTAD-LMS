package co.istad.lms.features.admission.dto;

import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AdmissionDetailResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String email,

        String phoneNumber,

        LocalDate dob,
        String gender,
        String avatar,
        String address,
        String familyPhoneNumber,
        String biography,
        ShiftResponse shift,
        StudyProgramResponse studyProgram,
        DegreeResponse degree,
        String createdBy,
        LocalDateTime createdAt,
        String lastModifiedBy,
        LocalDateTime lastModifiedAt,
        Boolean isDeleted
) {
}
