package co.istad.lms.features.faculties.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FacultyRequest(

        @NotBlank(message = "alias is required")
        @Size(max = 100, message = "alias cannot be longer than 100 characters")
        String alias,

        @NotBlank(message = "name is required")
        @Size(max = 100, message = "name cannot be longer than 100 characters")
        String name,

        String description,

        String logo,

        String address,

        @NotNull(message = "isDraft is required")
        Boolean isDraft
) {
}
