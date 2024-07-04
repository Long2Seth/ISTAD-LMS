package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.*;

public record ScoreUpdateRequest(

        @Min(value = 0, message = "activityScore must be between 0 and 100")
        @Max(value = 100, message = "activityScore must be between 0 and 100")
        Double activityScore,

        @Min(value = 0, message = "attendanceScore must be between 0 and 100")
        @Max(value = 100, message = "attendanceScore must be between 0 and 100")
        Double attendanceScore,

        @Min(value = 0, message = "midtermExamScore must be between 0 and 100")
        @Max(value = 100, message = "midtermExamScore must be between 0 and 100")
        Double midtermExamScore,

        @Min(value = 0, message = "finalExamScore must be between 0 and 100")
        @Max(value = 100, message = "finalExamScore must be between 0 and 100")
        Double finalExamScore,

        @Min(value = 0, message = "miniProjectScore must be between 0 and 100")
        @Max(value = 100, message = "miniProjectScore must be between 0 and 100")
        Double miniProjectScore,

        @Min(value = 0, message = "assignmentScore must be between 0 and 100")
        @Max(value = 100, message = "assignmentScore must be between 0 and 100")
        Double assignmentScore

) {
}
