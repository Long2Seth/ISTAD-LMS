package co.istad.lms.features.attendance.dto;

import lombok.Builder;

@Builder
public record AttendanceUpdateRequest(

        Integer status,
        String note

) {
}

