package co.istad.lms.features.shift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShiftRequest(


        @NotBlank(message = "alias is required")
        @Size(max = 100, message = "alias cannot be longer than 100 characters")
        String alias,

        @NotBlank(message = "name is required")
        @Size(max = 100, message = "name cannot be longer than 100 characters")
        String name,

        @NotNull(message = "Start time is required")
        String startTime,

        @NotNull(message = "End time is required")
        String endTime,

        @NotNull(message = "Weekday is required")
        Boolean weekday,


        @NotNull(message = "isDraft is required")
        Boolean isDraft,

        String description
) {
}
