package co.istad.lms.features.attendance.dto;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record AttendanceDetailResponse(

        String uuid,
        Boolean status,
        String note,
        String studentAlias,
        String lectureAlias

) {
}


