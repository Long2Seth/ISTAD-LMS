package co.istad.lms.features.degree.dto;

import java.time.LocalDateTime;

public record DegreeDetailResponse(
        String alias,
        String level,
        String description,
        Boolean isDraft,
        Boolean isDeleted
) {
}
