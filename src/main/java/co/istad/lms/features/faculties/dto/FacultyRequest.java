package co.istad.lms.features.faculties.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FacultyRequest(

        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name cannot be longer than 50 characters")
        String name,

        String description,

        String logo,

        String address
) {
}
