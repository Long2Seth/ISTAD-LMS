package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequestDetail;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;

import java.time.LocalDate;
import java.util.Set;

public record StaffResponseDetail(

        String uuid,


        String position,


        String highSchool,


        LocalDate highSchoolGraduationDate,


        LocalDate degreeGraduationDate,


        Set<String> educations,



        Set<String> skills,



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


        String bio,



        String linkGit,



        String linkLinkedin,



        String linkTelegram,



        String uploadCv,



        String identityCard,



        String birthPlace

) {
}
