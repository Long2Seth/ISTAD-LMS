package co.istad.lms.features.attendance.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AttendanceUpdateRequest(

        @Min(value = 1, message = "Status must be 1, 2, or 3")
        @Max(value = 3, message = "Status must be 1, 2, or 3")
        Integer status,
        String note

) {
}

