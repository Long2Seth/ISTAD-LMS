package co.istad.lms.features.faculties.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.swing.text.StyledEditorKit;

public record FacultyUpdateRequest(


        @Size(max = 50, message = "Name cannot be longer than 50 characters")
        String name,


        String description,

        String address

) {
}