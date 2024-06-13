package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

import java.time.LocalDate;

public record AcademicRequestUpdate(

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

        String gender,

        LocalDate dob,

        String email,

        String profileImage,

        String phoneNumber,

        String currentAddress,

        JsonBirthPlace birthPlace


) {
}
