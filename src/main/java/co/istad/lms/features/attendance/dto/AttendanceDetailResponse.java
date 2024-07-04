package co.istad.lms.features.attendance.dto;

import co.istad.lms.features.course.dto.CourseAttendanceResponse;
import co.istad.lms.features.course.dto.CourseLectureResponse;
import co.istad.lms.features.lecture.dto.LectureAttendanceResponse;
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
        String classCode,

        CourseAttendanceResponse course,
        StudentResponse student,
        LectureAttendanceResponse lecture
) {
}


