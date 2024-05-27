package co.istad.lms.features.subject.dto;

import jakarta.persistence.Column;

public record SubjectDetailResponse(

        String alias,
        String subjectName,
        String description,
        String subjectLogo,
        Integer credit,
        Integer duration

) {
}


