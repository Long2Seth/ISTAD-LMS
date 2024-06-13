package co.istad.lms.features.studentadmisson.dto;

import co.istad.lms.domain.Admission;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Shift;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;

import java.time.LocalDate;

public record StudentAdmissionDetailResponse(

        String uuid,
        String nameEn,
        String nameKh,
        String email,
        String highSchool,
        String phoneNumber,
        LocalDate dob,
        String birthPlace,
        String bacIiGrade,
        String gender,
        String avatar,
        String address,
        String guardianContact,
        String guardianRelationShip,
        String knownIstad,
        String identity,
        String biography,
        Boolean isDeleted,
        ShiftResponse shift,
        StudyProgramResponse studyProgram,
        DegreeResponse degree,
        AdmissionResponse admission
) {
}