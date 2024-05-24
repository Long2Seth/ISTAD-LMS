package co.istad.lms.features.material.dto;


import jakarta.persistence.Column;

public record MaterialDetailResponse(

        String alias,
        String title,
        String contentType,
        String extension,
        Long size,
        String fileUrl,
        String thumbnail,
        String description,
        String subjectAlias

) {
}

