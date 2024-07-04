package co.istad.lms.features.lecture.dto;

import java.time.LocalDate;

public record LectureAttendanceResponse(
        String uuid,
        String startTime,
        String endTime,
        String description,
        LocalDate lectureDate
) {
}
