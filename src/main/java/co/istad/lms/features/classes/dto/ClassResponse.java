package co.istad.lms.features.classes.dto;

import java.util.Set;

public record ClassResponse(

        String alias,
        String className,

        String instructorAlias,
        String studyProgramAlias,
        String shiftAlias,
        String generationAlias
//        Set<String> studentAliases
) {
}
