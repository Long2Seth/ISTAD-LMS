package co.istad.lms.features.attendance.dto;

import lombok.Builder;

@Builder
public record AttendanceUpdateRequest(


        String uuid,
        String note,
        String studentAlias,
        String lectureAlias

) {
}

