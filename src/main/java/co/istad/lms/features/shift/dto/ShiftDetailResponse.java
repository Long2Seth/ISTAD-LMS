package co.istad.lms.features.shift.dto;

import java.time.LocalTime;
import java.time.LocalDateTime;

public record ShiftDetailResponse(
        String alias,
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String weekday,
        String description,
        Boolean isDeleted
) {
}
