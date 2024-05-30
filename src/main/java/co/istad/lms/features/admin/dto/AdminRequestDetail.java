package co.istad.lms.features.admin.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequestDetail;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public record AdminRequestDetail(
        String highSchool,
        LocalDate highSchoolGraduationDate,
        String degree,
        LocalDate degreeGraduationDate,
        String major,
        String studyAtUniversityOrInstitution,
        String experienceAtWorkingPlace,
        Integer experienceYear,
        UserRequestDetail user
) {
}
