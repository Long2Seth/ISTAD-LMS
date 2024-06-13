package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ScoreUpdateRequest(

        @Positive(message = "activityScore must be positive")
        Double activityScore,

        @Positive(message = "attendanceScore must be positive")
        Double attendanceScore,

        @Positive(message = "midtermExamScore must be positive")
        Double midtermExamScore,

        @Positive(message = "finalExamScore must be positive")
        Double finalExamScore,

        @Positive(message = "miniProjectScore must be positive")
        Double miniProjectScore,

        @Positive(message = "assignmentScore must be positive")
        Double assignmentScore
) {
}
