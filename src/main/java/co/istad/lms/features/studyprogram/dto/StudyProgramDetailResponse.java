package co.istad.lms.features.studyprogram.dto;

public record StudyProgramDetailResponse(

        String alias,
        String studyProgramName,
        String description,
        String logo,
        Boolean isDeleted,
        Long facultyId
) {
}
