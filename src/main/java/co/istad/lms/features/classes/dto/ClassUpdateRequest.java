package co.istad.lms.features.classes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record ClassUpdateRequest(

        @Size(max = 100,message = "classCode cannot be longer than 100 characters")
        String classCode,

        String description,

        @Size(max = 100, message = "instructorUuid cannot be longer than 100 characters")
        String instructorUuid,

        @Size(max = 100, message = "studyProgramAlias cannot be longer than 100 characters")
        String studyProgramAlias,

        @Size(max = 100, message = "shiftAlias cannot be longer than 100 characters")
        String shiftAlias,

        @Size(max = 100, message = "generationAlias cannot be longer than 100 characters")
        String generationAlias

) {
}
