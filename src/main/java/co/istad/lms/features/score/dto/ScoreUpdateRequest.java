package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScoreUpdateRequest(

        Double activityScore,
        Double attendanceScore,
        Double midtermExamScore,
        Double finalExamScore,
        Double miniProjectScore,
        Double assignmentScore
) {
}
