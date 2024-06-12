package co.istad.lms.features.lecture.dto;

import co.istad.lms.features.course.dto.CourseResponse;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record LectureDetailResponse(

        String uuid,
        String startTime,
        String endTime,
        String description,
        LocalDate lectureDate,
        Boolean isDeleted,
        Boolean isDraft,
        CourseResponse courseResponse

) {}


