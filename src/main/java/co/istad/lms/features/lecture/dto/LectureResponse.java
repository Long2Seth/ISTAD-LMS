package co.istad.lms.features.lecture.dto;

import co.istad.lms.features.course.dto.CourseLectureResponse;

import java.time.LocalDate;
import java.time.LocalTime;

public record LectureResponse(

        String uuid,
        String startTime,
        String endTime,
        String description,
        LocalDate lectureDate,
        Boolean isDeleted,
        Boolean isDraft,
        Integer status,
        String teachingType,

        String classCode
) {
}
