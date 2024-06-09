package co.istad.lms.features.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AttendanceRequest(

        @NotNull(message = "status is requirement")
        Integer status,
        String note,
        @NotBlank(message = "Student uuid is required")
        String studentUuid,
        @NotBlank(message = "Lecture alias is required")
        String lectureAlias

) {
}
