package co.istad.lms.features.faculties.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.swing.text.StyledEditorKit;

public record FacultyUpdateRequest(

        @Size(max = 100, message = "name cannot be longer than 100 characters")
        String alias,

        @Size(max = 100, message = "name cannot be longer than 100 characters")
        String name,

        String description,

        String logo,

        String address

) {
}