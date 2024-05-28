package co.istad.lms.features.admin.dto;

import co.istad.lms.features.user.dto.UserRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AdminRequest(

        @NotBlank(message = "High School name is required")
        @Size(max = 50, message = "High School name must be less than or equal to 50 characters")
        String highSchool,

        @NotNull(message = "High School graduation date is required")
        @PastOrPresent(message = "High School graduation date must be in the past or present")
        LocalDate highSchoolGraduationDate,

        @Size(max = 50, message = "Degree must be less than or equal to 50 characters")
        String degree, // Bachelor, Master, Doctor

        @PastOrPresent(message = "Degree graduation date must be in the past or present")
        LocalDate degreeGraduationDate,

        @Size(max = 50, message = "Major must be less than or equal to 50 characters")
        String major,

        @Size(max = 50, message = "Institution name must be less than or equal to 50 characters")
        String studyAtUniversityOrInstitution,

        @Size(max = 50, message = "Experience at working place must be less than or equal to 50 characters")
        String experienceAtWorkingPlace,

        @PastOrPresent(message = "Experience year must be in the past or present")
        LocalDate experienceYear, // experience compare per year

        boolean status,

        boolean isDeleted,

        @NotNull(message = "UserRequest is required")
        @Valid
        UserRequest userRequest

) {
}
