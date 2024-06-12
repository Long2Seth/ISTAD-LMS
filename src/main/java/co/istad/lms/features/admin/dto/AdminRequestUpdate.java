package co.istad.lms.features.admin.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

import java.time.LocalDate;

public record AdminRequestUpdate(

        String highSchool,

        LocalDate highSchoolGraduationDate,

        String degree,

        LocalDate degreeGraduationDate,

        String major,

        String studyAtUniversityOrInstitution,

        String experienceAtWorkingPlace,

        Integer experienceYear,

        // filed user
        String nameEn,

        String nameKh,

        String username,

        String gender,

        LocalDate dob,

        String email,

        String profileImage,

        String phoneNumber,

        String cityOrProvince,

        String khanOrDistrict,

        String sangkatOrCommune,

        String street,

        JsonBirthPlace birthPlace


) {
}
