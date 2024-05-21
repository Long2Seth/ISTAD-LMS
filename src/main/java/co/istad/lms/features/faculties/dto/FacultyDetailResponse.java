package co.istad.lms.features.faculties.dto;

public record FacultyDetailResponse(
        Long id,
        String alias,
        String name,
        String description,
        String address,
        Boolean isDeleted
) {
}