package co.istad.lms.features.shift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShiftUpdateRequest(

        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @Size(max = 100, message = "Name cannot be longer than 100 characters")
        String name,

        @NotNull(message = "Start time is required")
        @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = "Invalid time format, should be HH:mm:ss")
        LocalTime startTime,

        @NotNull(message = "End time is required")
        @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = "Invalid time format, should be HH:mm:ss")
        LocalTime endTime,

        @NotNull(message = "Weekday is required")
        Boolean weekday,

        String description


) {
}
