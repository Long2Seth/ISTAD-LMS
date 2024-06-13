package co.istad.lms.features.studentadmisson.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentAdmissionRequest(

        @NotBlank(message = "nameEn is required")
        @Size(max = 100, message = "nameEn cannot be longer than 100 characters")
        String nameEn,

        @NotBlank(message = "nameKh is required")
        @Size(max = 100, message = "nameKh cannot be longer than 100 characters")
        String nameKh,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 50, message = "Email cannot be longer than 50 characters")
        String email,

        @NotBlank(message = "High School is required")
        String highSchool,

        @Size(max = 20, message = "Phone Number cannot be longer than 20 characters")
        @Pattern(regexp = "(\\+855[0-9]+|0[0-9]+)", message = "format: +855(number) or 0(number)")
        String phoneNumber,

        @NotBlank(message = "Date of Birth is required")
        String dob,

        String birthPlace,

        @Size(max = 10, message = "Bac II Grade cannot be longer than 10 characters")
        String bacIiGrade,

        @NotBlank(message = "Gender is required")
        @Size(max = 20, message = "Gender cannot be longer than 20 characters")
        String gender,

        String address,

        @Size(max = 50, message = "Guardian Contact cannot be longer than 50 characters")
        String guardianContact,

        @Size(max = 50, message = "Guardian Relationship cannot be longer than 50 characters")
        String guardianRelationShip,

        String knownIstad,

        String identity,
        String avatar,

        String biography,

        @NotBlank(message = "Shift alias is required")
        @Size(max = 100,message = "shiftAlias can not be longer than 100 characters")
        String shiftAlias,

        @NotBlank(message = "Study Program alias is required")
        @Size(max = 100,message = "studyProgramAlias can not be longer than 100 characters")
        String studyProgramAlias,

        @NotBlank(message = "Degree alias is required")
        @Size(max = 100,message = "degreeAlias can not be longer than 100 characters")
        String degreeAlias
) {
}
