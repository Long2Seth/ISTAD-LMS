package co.istad.lms.features.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AttendanceRequest(


        String note,
        @NotBlank(message = "Student alias is required")
        String studentAlias,
        @NotBlank(message = "Lecture alias is required")
        String lectureAlias

) {
}
