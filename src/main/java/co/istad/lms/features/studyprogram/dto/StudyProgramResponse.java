package co.istad.lms.features.studyprogram.dto;

public record StudyProgramResponse(

        String alias,
        String studyProgramName,
        String description,
        Long facultyId
) {
}
