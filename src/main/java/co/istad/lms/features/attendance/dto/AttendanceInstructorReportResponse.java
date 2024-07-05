package co.istad.lms.features.attendance.dto;

import co.istad.lms.features.course.dto.CourseAttendanceResponse;
import co.istad.lms.features.lecture.dto.LectureAttendanceResponse;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentScoreResponse;

public record AttendanceInstructorReportResponse (
        String uuid,
        Integer status,
        String note,
        String classCode,

        Integer p,

        Integer ea,
        Integer ua,

        Double totalScore,

        CourseAttendanceResponse course,
        StudentScoreResponse student,
        LectureAttendanceResponse lecture
){
}
