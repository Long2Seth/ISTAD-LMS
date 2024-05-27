package co.istad.lms.features.attendance.dto;

import co.istad.lms.features.lecture.dto.LectureResponse;
import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record AttendanceDetailResponse(

        String uuid,
        Boolean status,
        String note,
        String studentAlias,
        LectureResponse lecture
) {
}


