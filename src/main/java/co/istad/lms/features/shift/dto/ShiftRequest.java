package co.istad.lms.features.shift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
        LocalDateTime startTime,

        @NotNull(message = "End time is required")
        LocalDateTime endTime,

        @NotBlank(message = "Weekday is required")
        @Size(max = 10, message = "Weekday cannot be longer than 10 characters")
        Boolean weekday,

        String description,

        @NotNull(message = "isDeleted is required")
        Boolean isDeleted
) {
}
