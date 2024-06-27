package co.istad.lms.features.score.dto;

import jakarta.validation.constraints.*;

public record ScoreRequest(

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
        Double assignmentScore,

        @NotBlank(message = "Student uuid is required")
        @Size(max = 100, message = "studentUuid can not be longer than 100 characters")
        String studentUuid,

        @NotBlank(message = "Course alias is required")
        @Size(max = 100, message = "courseUuid can not be longer than 100 characters")
        String courseUuid
) {
}
