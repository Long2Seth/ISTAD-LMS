package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponseDetail;

import java.time.LocalDate;
import java.util.Set;

public record InstructorResponseDetail(
        String uuid,

        String highSchool,

        String highSchoolGraduationDate,

        Set<String> degree,

        String degreeGraduationDate,

        Set<String> major,

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
