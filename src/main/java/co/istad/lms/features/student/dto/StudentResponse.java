package co.istad.lms.features.student.dto;

import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.UserResponse;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record StudentResponse(

        String nameEn,


        String nameKh,



        String gender,



        String email,



        String phoneNumber,



        Set< String> educations,




        Set<String> skills,




        String birthPlace,




        String currentAddress,




        String bio,



        String dob,




        String linkGit,




        String linkLinkedin,




        String linkTelegram,





        String uploadCv,




        String identityCard
) {
}
