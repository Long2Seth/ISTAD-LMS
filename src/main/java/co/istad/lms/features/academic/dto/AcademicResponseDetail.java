package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;

public record AcademicResponseDetail(
        String uuid,
        String highSchool,

        LocalDate highSchoolGraduationDate,

        String degree,

        LocalDate degreeGraduationDate,

        String major,

        String studyAtUniversityOrInstitution,

        String experienceAtWorkingPlace,

        LocalDate experienceYear,

        UserResponse users
) {
}