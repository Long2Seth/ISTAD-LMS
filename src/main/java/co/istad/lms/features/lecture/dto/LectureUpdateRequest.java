package co.istad.lms.features.lecture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LectureUpdateRequest(

        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,
        String startTime,
        String endTime,
        String description,
        String lectureDate,
        String courseAlias

) {
}
