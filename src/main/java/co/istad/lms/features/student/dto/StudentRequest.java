package co.istad.lms.features.student.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record StudentRequest(
        @NotBlank(message = "English name is required")
        @Size(max = 50, message = "English name must be less than or equal to 50 characters")
        String nameEn,

        @NotBlank(message = "Khmer name is required")
        @Size(max = 50, message = "Khmer name must be less than or equal to 50 characters")
        String nameKh,

        @NotBlank(message = "Gender is required")
        @Size(max = 10, message = "Gender must be less than or equal to 10 characters")
        String gender,

        @NotNull(message = "Date of birth is required")
        LocalDate dob,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must be less than or equal to 100 characters")
        String email,

        @NotBlank(message = "Profile image is required")
        @Size(max = 255, message = "Profile image must be less than or equal to 255 characters")
        String profileImage,

        @Size(max = 20, message = "Phone number must be less than or equal to 20 characters")
        String phoneNumber,

        @NotBlank(message = "High school is required")
        String highSchool

        // Add other necessary fields here with appropriate validation
) {
}
