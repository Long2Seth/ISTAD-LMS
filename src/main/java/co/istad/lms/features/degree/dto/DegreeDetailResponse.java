package co.istad.lms.features.degree.dto;

import java.time.LocalDateTime;

public record DegreeDetailResponse(
        String alias,
        String level,
        String description,
        String lastModifiedBy,
        Boolean isDeleted,

        Boolean isDraft,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt
) {
}
