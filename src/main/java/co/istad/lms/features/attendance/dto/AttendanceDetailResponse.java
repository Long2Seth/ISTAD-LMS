package co.istad.lms.features.attendance.dto;

import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.student.dto.StudentResponse;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AttendanceDetailResponse(

        String uuid,
        Integer status,
        String note,
        StudentResponse student,
        LectureResponse lecture
) {
}


