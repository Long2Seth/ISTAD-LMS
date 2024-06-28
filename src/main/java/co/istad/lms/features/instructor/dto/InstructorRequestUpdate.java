package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

import java.time.LocalDate;
import java.util.Set;

public record InstructorRequestUpdate(

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
