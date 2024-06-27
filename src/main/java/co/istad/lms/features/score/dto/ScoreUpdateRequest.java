package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ScoreUpdateRequest(

        @Min(value = 0,message = "activityScore is 0 or greater")
        Double activityScore,

        @Min(value = 0,message = "attendanceScore  is 0 or greater")
        Double attendanceScore,

        @Min(value = 0,message = "midtermExamScore  is 0 or greater")
        Double midtermExamScore,

        @Min(value = 0,message = "activityScore  is 0 or greater")
        Double finalExamScore,

        @Min(value = 0,message = "finalExamScore  is 0 or greater")
        Double miniProjectScore,

        @Min(value = 0,message = "assignmentScore  is 0 or greater")
        Double assignmentScore
) {
}
