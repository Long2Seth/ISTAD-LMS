package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ScoreRequest(

        @Positive(message = "activityScore must be positive")
        Double activityScore,

        @Positive(message = "attendanceScore must be positive")
        Double attendanceScore,

        @Positive(message = "midtermExamScore must be positive")
        Double midtermExamScore,

        @Positive(message = "activityScore must be positive")
        Double finalExamScore,

        @Positive(message = "finalExamScore must be positive")
        Double miniProjectScore,

        @Positive(message = "assignmentScore must be positive")
        Double assignmentScore,

        @NotBlank(message = "Student uuid is required")
        @Size(max = 100, message = "studentUuid can not be longer than 100 characters")
        String studentUuid,

        @NotBlank(message = "Course alias is required")
        @Size(max = 100, message = "courseUuid can not be longer than 100 characters")
        String courseUuid
) {
}
