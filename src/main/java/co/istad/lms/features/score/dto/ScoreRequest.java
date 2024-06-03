package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScoreRequest(
        @NotNull(message = "Semester is required")
        Integer semester,

        Double activityScore,
        Double attendanceScore,
        Double midtermExamScore,
        Double finalExamScore,
        Double miniProjectScore,
        Double assignmentScore,

        @NotBlank(message = "Student alias is required")
        String studentAlias,

        @NotBlank(message = "Course alias is required")
        String courseAlias
) {
}
