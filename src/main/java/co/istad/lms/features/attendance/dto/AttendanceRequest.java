package co.istad.lms.features.attendance.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record AttendanceRequest(

        @NotNull(message = "status is require")
        @Min(value = 1, message = "Status must be 1, 2, or 3")
        @Max(value = 3, message = "Status must be 1, 2, or 3")
        Integer status,
        String note,
        @NotBlank(message = "Student uuid is required")
        @Size(max = 100)
        String studentUuid,
        @NotBlank(message = "Lecture alias is required")
        @Size(max = 100)
        String lectureUuid

) {
}
