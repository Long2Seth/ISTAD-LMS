package co.istad.lms.features.lecture.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record LectureResponse(

        String uuid,
        LocalTime startTime,
        LocalTime endTime,
        LocalDate lectureDate

) {
}
