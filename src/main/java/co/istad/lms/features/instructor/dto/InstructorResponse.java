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

        String uuid,


        String nameEn,


        String nameKh,

        String username,


        String gender,


        String position,


        String dob,


        String email,


        String profileImage,


        String phoneNumber,


        String bio,

        String linkGit,

        String linkLinkedin,

        String linkTelegram,

        String uploadCv,

        String identity,


        Set<@NotBlank(message = "Degree is required") String> degree,


        Set<@NotBlank(message = "Major is required") String> major,

        JsonBirthPlace birthPlace

) {

}
