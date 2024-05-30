package co.istad.lms.features.studentadmisson.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentAdmissionRequest(

        @NotBlank(message = "Name (English) is required")
        @Size(max = 50, message = "Name (English) cannot be longer than 50 characters")
        String nameEn,

        @NotBlank(message = "Name (Khmer) is required")
        @Size(max = 50, message = "Name (Khmer) cannot be longer than 50 characters")
        String nameKh,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 50, message = "Email cannot be longer than 50 characters")
        String email,

        @NotBlank(message = "High School is required")
        String highSchool,

        @Size(max = 50, message = "Phone Number cannot be longer than 50 characters")
        String phoneNumber,

        @NotNull(message = "Date of Birth is required")
        LocalDate dob,

        String pob,

        @Size(max = 10, message = "Bac II Grade cannot be longer than 10 characters")
        String bacIiGrade,

        @NotBlank(message = "Gender is required")
        @Size(max = 20, message = "Gender cannot be longer than 20 characters")
        String gender,

        String avatar,

        String address,

        @Size(max = 50, message = "Guardian Contact cannot be longer than 50 characters")
        String guardianContact,

        @Size(max = 50, message = "Guardian Relationship cannot be longer than 50 characters")
        String guardianRelationShip,

        String knownIstad,

        String identity,

        String biography,

        @NotBlank(message = "Shift alias is required")
        String shiftAlias,

        @NotBlank(message = "Study Program alias is required")
        String studyProgramAlias,

        @NotBlank(message = "Degree alias is required")
        String degreeAlias,

        @NotBlank
        String admissionAlias
) {
}
