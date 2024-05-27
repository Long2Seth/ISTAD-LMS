package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserRequest;

import java.time.LocalDate;

public record AcademicRequest(
        String highSchool,

        LocalDate highSchoolGraduationDate,

        String degree, // Bachelor, Master, Doctor

        LocalDate degreeGraduationDate,

        String major,

        String studyAtUniversityOrInstitution,

        String experienceAtWorkingPlace,

        LocalDate experienceYear, // experience compare per year

        boolean status,

        boolean isDeleted,

        UserRequest userRequest
) {
}
