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

        @Size(max = 100, message = "alias cannot be longer than 100 characters")
        String alias,

        @Size(max = 100, message = "name cannot be longer than 100 characters")
        String name,

        String startTime,

        String endTime,

        @NotNull(message = "Weekday is required")
        Boolean weekday,

        String description


) {
}
