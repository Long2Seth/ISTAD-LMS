package co.istad.lms.features.subject.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;

public record SubjectDetailResponse(

        String alias,
        String title,
        String description,
        String logo,
        Integer practice,
        Integer internship,
        Integer theory,
        Integer duration,

        JsonNode curriculum,

        Boolean isDeleted,

        Boolean isDraft

) {
}


