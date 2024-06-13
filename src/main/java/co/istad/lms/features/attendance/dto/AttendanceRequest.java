package co.istad.lms.features.attendance.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record AttendanceRequest(

        @NotNull(message = "status is require")
        @Max(value = 3,message = "max value is 3")
        @Min(value = 1,message = "min value is 1")
        Integer status,
        String note,
        @NotBlank(message = "Student uuid is required")
        @Size(max = 100,message = "studentUuid cannot be longer than 100 characters")
        String studentUuid,
        @NotBlank(message = "Lecture alias is required")
        @Size(max = 100,message = "studentUuid cannot be longer than 100 characters")
        String lectureUuid

) {
}
