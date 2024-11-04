package co.istad.lms.features.score.dto;

public record ScoreEachCourseRequest(
        String generationAlias,
        String studyProgramAlias,
        String classUuid,
        String courseAlias
) {
}
