package co.istad.lms.features.material.dto;


import jakarta.persistence.Column;

public record MaterialDetailResponse(

        String alias,
        String title,
        String contentType,
        String extension,
        Long size,
        String fileName,
        String fileUrl,
        String description,
        String subjectAlias,

        Boolean isDeleted,
        Boolean isDraft
) {
}

