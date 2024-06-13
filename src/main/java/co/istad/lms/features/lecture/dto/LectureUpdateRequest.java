package co.istad.lms.features.lecture.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LectureUpdateRequest(

        String startTime,
        String endTime,
        String description,
        String lectureDate

) {
}
