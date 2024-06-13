package co.istad.lms.features.classes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import javax.swing.text.StyledEditorKit;
import java.util.Set;

public record ClassRequest(

        @NotBlank(message = "Class name cannot be null")
        String className,

        @NotNull(message = "Year is require")
        @Positive(message = "year must be positive")
        Integer year,

        String description,

        @Size(max = 100, message = "instructorUuid must be shorter than 100")
        String instructorUuid,

        @NotBlank(message = "Study Program alias cannot be null")
        @Size(max = 100, message = "studyProgramAlias must shorter than 100")
        String studyProgramAlias,

        @NotBlank(message = "Shift alias cannot be null")
        @Size(max = 100, message = "shiftAlias must shorter than 100")
        String shiftAlias,

        @NotBlank(message = "Generation alias cannot be null")
        @Size(max = 100,message = "generationAlias must shorter than 100")
        String generationAlias,

        @Size(max = 100,message = "studentUuid must shorter than 100")
        Set<String> studentUuid,

        @NotNull(message = "isDraft is require")
        Boolean isDraft
) {
}
