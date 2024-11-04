package co.istad.lms.features.graduation.dto;

public record GraduationRequest(
        String score,
        String gpa,
        String grade
) {
}
