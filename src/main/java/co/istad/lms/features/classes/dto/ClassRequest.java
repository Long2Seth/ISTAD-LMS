package co.istad.lms.features.classes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.swing.text.StyledEditorKit;
import java.util.Set;

public record ClassRequest(
        @NotBlank(message = "Alias cannot be null")
        String alias,

        @NotBlank(message = "Class name cannot be null")
        String className,

        String description,

        String instructorUuid,

        @NotBlank(message = "Study Program alias cannot be null")
        String studyProgramAlias,

        @NotBlank(message = "Shift alias cannot be null")
        String shiftAlias,

        @NotBlank(message = "Generation alias cannot be null")
        String generationAlias,

        Set<String> studentUuid,

        @NotNull(message = "isDraft is require")
        Boolean isDraft
) {
}
