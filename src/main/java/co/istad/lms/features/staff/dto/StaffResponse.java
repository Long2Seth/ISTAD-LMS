package co.istad.lms.features.staff.dto;


import co.istad.lms.features.user.dto.UserResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record StaffResponse(

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
