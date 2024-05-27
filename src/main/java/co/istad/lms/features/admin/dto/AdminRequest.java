package co.istad.lms.features.admin.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserRequest;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AdminRequest(

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
