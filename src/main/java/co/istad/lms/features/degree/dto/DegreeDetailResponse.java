package co.istad.lms.features.degree.dto;

import java.time.LocalDateTime;

public record DegreeDetailResponse(
        String alias,
        String level,
        String description,
        String modifiedBy,
        Boolean isDeleted,

        Boolean isDfraft,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt
) {
}
