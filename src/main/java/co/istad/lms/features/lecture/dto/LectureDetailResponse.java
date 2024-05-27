package co.istad.lms.features.lecture.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record LectureDetailResponse(

        String alias,
        String startTime,
        String endTime,
        String description,
        LocalDate lectureDate,
        Boolean status,
        Boolean isDeleted,
        String courseAlias

) {}


