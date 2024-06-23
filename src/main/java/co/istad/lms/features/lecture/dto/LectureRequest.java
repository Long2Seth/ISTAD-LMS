package co.istad.lms.features.lecture.dto;

import jakarta.validation.constraints.*;

public record LectureRequest(

        @NotBlank(message = "startTime is required")
        String startTime,
        @NotBlank(message = "endTime is required")
        String endTime,
        String description,
        @NotBlank(message = "lectureDate is required")
        String lectureDate,

        @NotNull(message = "status is require")
        @Min(value = 1, message = "status must be between 1-3")
        @Max(value = 3, message = "status must be between 1-3")
        Integer status,

        @NotBlank(message = "teachingType is require")
        String teachingType,

        @Size(max = 100, message = "curseUuid cannot be longer than 100 characters")
        String courseUuid,
        @NotNull(message = "isDraft is required")
        Boolean isDraft
) {
}