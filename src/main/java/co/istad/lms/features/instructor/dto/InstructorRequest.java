package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequest;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record InstructorRequest(
        @NotBlank(message = "English name is required")
        @Size(max = 50, message = "English name must be less than or equal to 50 characters")
        String nameEn,

        @NotBlank(message = "Khmer name is required")
        @Size(max = 50, message = "Khmer name must be less than or equal to 50 characters")
        String nameKh,

        @NotBlank(message = "Gender is required")
        @Size(max = 10, message = "Gender must be less than or equal to 10 characters")
        String gender,

        @NotBlank(message = "Position is required")
        @Size(max = 255, message = "Position must be less than or equal to 255 characters")
        String position,

        @NotNull(message = "Date of birth is required")
        String dob,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must be less than or equal to 100 characters")
        String email,

        @NotBlank(message = "Profile image is required")
        @Size(max = 255, message = "Profile image must be less than or equal to 255 characters")
        String profileImage,

        @NotBlank(message = "Phone number is required")
        @Size(max = 20, message = "Phone number must be less than or equal to 20 characters")
        String phoneNumber,


        @NotNull(message = "Bio is required")
        @Size(max = 255, message = "Bio must be less than or equal to 255 characters")
        String bio,

        String linkGit,

        String linkLinkedin,

        String linkTelegram,

        String uploadCv,

        String identity,

        @ElementCollection
        Set<@NotBlank(message = "Degree is required") String> degree,

        @ElementCollection
        Set<@NotBlank(message = "Major is required") String> major,

        @NotNull(message = "Birth place is required")
        @Valid
        JsonBirthPlace birthPlace

) {}

