package co.istad.lms.features.shift.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ShiftUpdateResponse(

        String alias,
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
