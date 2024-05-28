package co.istad.lms.features.classes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record ClassRequest(
        @NotBlank(message = "Alias cannot be null")
        String alias,

        @NotBlank(message = "Class name cannot be null")
        String className,

        String description,

        @NotBlank(message = "Instructor uuid cannot be null")
        String instructorUuid,

        @NotBlank(message = "Study Program alias cannot be null")
        String studyProgramAlias,

        @NotBlank(message = "Shift alias cannot be null")
        String shiftAlias,

        @NotBlank(message = "Generation alias cannot be null")
        String generationAlias,

        Set<String> studentUuid
) {
}
