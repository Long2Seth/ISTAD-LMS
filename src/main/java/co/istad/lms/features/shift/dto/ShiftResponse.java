package co.istad.lms.features.shift.dto;

import java.time.LocalTime;

public record ShiftResponse(

        String alias,
        String name,
        LocalTime startTime,

        LocalTime endTime
        ) {
}
