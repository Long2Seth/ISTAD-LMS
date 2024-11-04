package co.istad.lms.features.lecture.dto;

import co.istad.lms.features.course.dto.CourseLectureResponse;

import java.time.LocalDate;

public record LectureInstructorScheduleResponse(
        String uuid,
        String startTime,
        String endTime,
        String description,
        LocalDate lectureDate,

        Integer status,
        String teachingType,
        String courseTitle,
        String classCode
) {
}
