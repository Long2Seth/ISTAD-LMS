package co.istad.lms.features.attendance.dto;

import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.student.dto.StudentResponse;
import lombok.Builder;

@Builder
public record AttendanceResponse(

        String uuid,

        Integer status,

        StudentResponse student,
        LectureResponse lecture

) {
}
