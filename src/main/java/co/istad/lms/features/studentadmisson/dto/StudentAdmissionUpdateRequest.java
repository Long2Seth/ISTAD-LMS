package co.istad.lms.features.studentadmisson.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentAdmissionUpdateRequest(

        @Size(max = 50, message = "nameEn cannot be longer than 50 characters")
        String nameEn,

        @Size(max = 50, message = "nameKh cannot be longer than 50 characters")
        String nameKh,

        @Email(message = "email should be valid")
        @Size(max = 50, message = "email cannot be longer than 50 characters")
        String email,

        String highSchool,

        @Size(max = 20, message = "Phone Number cannot be longer than 20 characters")
        @Pattern(regexp = "(\\+855[0-9]+|0[0-9]+)", message = "format: +855(number) or 0(number)")
        String phoneNumber,

        LocalDate dob,

        String birthPlace,

        @Size(max = 10, message = "Bac II Grade cannot be longer than 10 characters")
        String bacIiGrade,

        @Size(max = 20, message = "Gender cannot be longer than 20 characters")
        String gender,

        String avatar,

        String address,

        @Size(max = 20, message = "Guardian Contact cannot be longer than 20 characters")
        @Pattern(regexp = "(\\+855[0-9]+|0[0-9]+)", message = "format: +855(number) or 0(number)")
        String guardianContact,

        @Size(max = 50, message = "Guardian Relationship cannot be longer than 50 characters")
        String guardianRelationShip,

        String knownIstad,

        String identity,

        String biography,

        @Size(max = 100,message = "shifAlias can not be longer than 100 characters")
        String shiftAlias,

        @Size(max = 100,message = "studyProgramAlias can not be longer than 100 characters")
        String studyProgramAlias,

        @Size(max = 100,message = "degreeAlias can not be longer than 100 characters")
        String degreeAlias
) {
}
