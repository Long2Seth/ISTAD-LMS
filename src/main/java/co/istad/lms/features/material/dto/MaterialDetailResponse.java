package co.istad.lms.features.material.dto;


import co.istad.lms.features.subject.dto.SubjectResponse;
import jakarta.persistence.Column;

public record MaterialDetailResponse(

        String uuid,
        String title,
        String contentType,
        String fileType,
        String extension,
        Long size,
        String fileName,
        String fileUrl,
        String description,
        SubjectResponse subject,

        Boolean isDeleted,
        Boolean isDraft
) {
}

