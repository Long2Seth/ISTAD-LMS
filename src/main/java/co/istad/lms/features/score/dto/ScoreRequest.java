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

        @NotBlank(message = "Student uuid is required")
        String studentUuid,

        @NotBlank(message = "Course alias is required")
        String courseUuid
) {
}
