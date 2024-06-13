package co.istad.lms.features.score.dto;

public record ScoreResponse(
        String uuid,
        Double activityScore,
        Double attendanceScore,
        Double midtermExamScore,
        Double finalExamScore,
        Double miniProjectScore,
        Double assignmentScore,
        String studentAlias,
        String courseAlias,
        Boolean isDeleted
) {
}
