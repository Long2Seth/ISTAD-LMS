package co.istad.lms.features.admin.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;
import java.util.List;

public record AdminResponse(
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
