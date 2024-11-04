package co.istad.lms.features.shift.dto;

import java.security.Timestamp;
import java.time.LocalTime;
import java.time.LocalDateTime;

public record ShiftDetailResponse(
        String alias,
        String name,
        String startTime,
        String endTime,
        Boolean weekday,
        String description,
        Boolean isDeleted,
        Boolean isDraft
) {
}
