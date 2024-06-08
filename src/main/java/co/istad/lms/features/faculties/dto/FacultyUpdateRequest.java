package co.istad.lms.features.faculties.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.swing.text.StyledEditorKit;

public record FacultyUpdateRequest(


        String alias,

        @Size(max = 50, message = "Name cannot be longer than 50 characters")
        String name,

        String description,

        String logo,

        String address

) {
}