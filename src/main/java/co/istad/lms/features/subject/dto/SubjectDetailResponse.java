package co.istad.lms.features.subject.dto;

import jakarta.persistence.Column;

public record SubjectDetailResponse(

        String alias,
        String subjectName,
        String description,
        String logo,
        Integer credit,
        Integer duration,

        Boolean isDeleted,

        Boolean isDraft

) {
}


