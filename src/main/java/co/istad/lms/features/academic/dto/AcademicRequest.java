package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserRequest;
import jakarta.persistence.ElementCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record AcademicRequest(

        @NotBlank(message = "English name is required")
        @Size(max = 50, message = "English name must be less than or equal to 50 characters")
        String nameEn,




        @NotBlank(message = "Khmer name is required")
        @Size(max = 50, message = "Khmer name must be less than or equal to 50 characters")
        String nameKh,




        @NotBlank(message = "Gender is required")
        @Size(max = 10, message = "Gender must be less than or equal to 10 characters")
        String gender,




        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must be less than or equal to 100 characters")
        String email,




        @NotBlank(message = "Phone number is required")
        @Size(max = 20, message = "Phone number must be less than or equal to 20 characters")
        @Pattern(regexp = "^[0-9]*$", message = "Phone number should input only number")
        String phoneNumber,




        @NotBlank(message = "Position is required")
        @Size(max = 255, message = "Position must be less than or equal to 255 characters")
        String position,




        @ElementCollection

        Set<@NotBlank(message = "Education is required") String> educations,





        @ElementCollection

        Set<@NotBlank(message = "Skill is required") String> skills,




        @NotNull(message = "Birth place is required")
        @Size(max = 255, message = "Birth place must be less than or equal to 255 characters")
        String birthPlace,




        @NotNull(message = "Current address is required")
        @Size(max = 255, message = "Current address must be less than or equal to 255 characters")
        String currentAddress,





        @Size(max = 255, message = "Bio must be less than or equal to 255 characters")
        String bio,



        @NotNull(message = "Date of birth is required")
        String dob,




        @Size(max = 255, message = "High school must be less than or equal to 255 characters")
        String linkGit,




        @Size(max = 255, message = "University or institution must be less than or equal to 255 characters")
        String linkLinkedin,




        @Size(max = 255, message = "Working place must be less than or equal to 255 characters")
        String linkTelegram,




        @Size(max = 255, message = "Experience at working place must be less than or equal to 255 characters")
        String uploadCv,




        @Size(max = 255, message = "Experience at working place must be less than or equal to 255 characters")
        String identityCard,

        @NotNull(message = "authorities is required")
        Set<String> authorityNames

) {
}
