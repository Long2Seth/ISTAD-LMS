package co.istad.lms.features.faculties.dto;

public record FacultyDetailResponse(
        String alias,
        String name,
        String description,
        String address,
        String logo,
        Boolean isDeleted
) {
}