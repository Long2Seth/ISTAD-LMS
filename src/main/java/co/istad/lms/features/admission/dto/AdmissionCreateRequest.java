package co.istad.lms.features.admission.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AdmissionCreateRequest(
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

        @NotNull(message = "Date of Birth is required")
        LocalDate dob,

        @NotBlank(message = "Gender is required")
        @Size(max = 20, message = "Gender cannot be longer than 20 characters")
        String gender,

        String avatar,

        String address,

        @Size(max = 50, message = "Family Phone Number cannot be longer than 50 characters")
        String familyPhoneNumber,

        String biography,

        @NotBlank(message = "Shift  is required")
        String shift,

        @NotBlank(message = "Study Program ID is required")
        String studyProgram,

        @NotBlank(message = "Degree UUID is required")
        String degreeUuid
) {
}
