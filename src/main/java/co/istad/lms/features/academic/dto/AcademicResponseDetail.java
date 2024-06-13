package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;

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

        Integer experienceYear,

        String nameEn,

        String nameKh,

        String username,

        String gender,

        LocalDate dob,

        String email,

        String profileImage,

        String phoneNumber,

        String currentAddress,

        JsonBirthPlace birthPlace


) {
}
