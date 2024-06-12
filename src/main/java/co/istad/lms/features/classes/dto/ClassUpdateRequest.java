package co.istad.lms.features.classes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record ClassUpdateRequest(


        String alias,

        String className,

        String description,


        String instructorUuid,


        String studyProgramAlias,


        String shiftAlias,

        String generationAlias

) {
}
