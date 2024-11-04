package co.istad.lms.features.academicyear.dto;

import jakarta.persistence.Column;

public record AcademicYearDetailResponse(

        String alias,
        String academicYear,

        Integer status,

        Boolean isDraft,

        Boolean isDeleted
        ) {
}