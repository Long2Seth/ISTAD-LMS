package co.istad.lms.features.instructor.dto;


import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponse;
import jakarta.persistence.ElementCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record InstructorResponse(

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
