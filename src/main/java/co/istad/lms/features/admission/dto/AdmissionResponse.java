package co.istad.lms.features.admission.dto;

import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;

import java.time.LocalDate;

public record AdmissionResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String email,
        LocalDate dob,
        String gender,
        String avatar,
        ShiftResponse shift,
        StudyProgramResponse studyProgram,
        DegreeResponse degree
) {

}
