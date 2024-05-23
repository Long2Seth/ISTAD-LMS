package co.istad.lms.features.shift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.security.Timestamp;
import java.time.LocalDateTime;

public record ShiftUpdateRequest(

        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @Size(max = 100, message = "Name cannot be longer than 100 characters")
        String name,

        LocalDateTime startTime,

        LocalDateTime endTime,


        Boolean weekday,

        String description


) {
}
