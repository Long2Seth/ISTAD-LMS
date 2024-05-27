package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;

public record AcademicResponse(
        String uuid,
        String highSchool,

        LocalDate highSchoolGraduationDate,

        String degree, // Bachelor, Master, Doctor

        LocalDate degreeGraduationDate,

        String major,

        String studyAtUniversityOrInstitution,

        String experienceAtWorkingPlace,

        LocalDate experienceYear, // experience compare per year

        UserResponse users
) {
}
