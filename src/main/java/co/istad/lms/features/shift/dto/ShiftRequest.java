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


        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot be longer than 100 characters")
        String name,

        @NotNull(message = "Start time is required")
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalTime startTime,

        @NotNull(message = "End time is required")
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        LocalTime endTime,

        @NotNull(message = "Weekday is required")
        Boolean weekday,


        @NotNull(message = "isDraft is required")
        Boolean isDraft,

        String description
) {
}
