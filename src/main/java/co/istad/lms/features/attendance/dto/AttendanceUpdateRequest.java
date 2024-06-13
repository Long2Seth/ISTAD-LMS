package co.istad.lms.features.attendance.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AttendanceUpdateRequest(

        @Max(value = 3,message = "max value is 3")
        @Min(value = 1,message = "min value is 1")
        Integer status,
        String note

) {
}

