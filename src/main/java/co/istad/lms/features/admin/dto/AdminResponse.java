package co.istad.lms.features.admin.dto;

import co.istad.lms.domain.User;
import co.istad.lms.features.user.dto.UserResponse;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record AdminResponse(

        String uuid,


        String position,


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
