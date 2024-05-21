package co.istad.lms.features.shift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ShiftUpdateRequest(
        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,


        @Size(max = 100, message = "Name cannot be longer than 100 characters")
        String name,

        LocalDateTime startTime,


        LocalDateTime endTime,


        @Size(max = 10, message = "Weekday cannot be longer than 10 characters")
        String weekday,

        String description


) {
}
