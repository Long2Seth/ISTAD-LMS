package co.istad.lms.features.classes.dto;

import jakarta.validation.constraints.*;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDate;
import java.util.Set;

public record ClassRequest(

        @NotBlank(message = "classCode is require")
        @Size(max = 100, message = "classCode cannot be longer than 100 characters")
        String classCode,

        @NotNull(message = "Year is require")
        @Positive(message = "year must be positive")
        Integer year,
        LocalDate classStart,

        LocalDate classEnd,

        String description,

        @Size(max = 100, message = "academicYearAlias cannot be longer than 100 characters")
        @NotBlank(message = "academicYearAlias is require")
        String academicYearAlias,

        @Size(max = 100, message = "instructorUuid cannot be longer than 100 characters")
        String instructorUuid,

        @NotBlank(message = "Study Program alias cannot be null")
        @Size(max = 100, message = "studyProgramAlias cannot be longer than 100 characters")
        String studyProgramAlias,

        @NotBlank(message = "Shift alias cannot be null")
        @Size(max = 100, message = "shiftAlias cannot be longer than 100 characters")
        String shiftAlias,

        @NotBlank(message = "Generation alias cannot be null")
        @Size(max = 100, message = "generationAlias cannot be longer than 100 characters")
        String generationAlias,

        @Size(max = 100, message = "studentUuid cannot be longer than 100 characters")
        Set<String> studentAdmissionUuid,

        @NotNull(message = "isDraft is require")
        Boolean isDraft,
        @NotNull(message = "status is require")
        @Max(value = 3, message = "status must be 1-3")
        @Min(value = 1, message = "status must be 1-3")
        Integer status
) {
}
