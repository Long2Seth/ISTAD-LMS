package co.istad.lms.features.degree.dto;

import java.time.LocalDateTime;

public record DegreeDetailResponse(
        String alias,
        String level,
        Integer numberOfYear,
        String description,
        Boolean isDraft,
        Boolean isDeleted
) {
}
