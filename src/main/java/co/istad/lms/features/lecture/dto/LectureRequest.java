package co.istad.lms.features.lecture.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LectureRequest(

        @NotBlank(message = "startTime is required")
        String startTime,
        @NotBlank(message = "endTime is required")
        String endTime,
        String description,
        @NotBlank(message = "lectureDate is required")
        String lectureDate,

        @NotBlank(message = "status is require")
        Boolean status,

        @Size(max = 100, message = "curseUuid cannot be longer than 100 characters")
        String courseUuid,
        @NotNull(message = "isDraft is required")
        Boolean isDraft
) {
}