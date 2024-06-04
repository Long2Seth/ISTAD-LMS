package co.istad.lms.features.subject.dto;

public record SubjectResponse(

        String alias,
        String title,
        Integer practice,
        Integer internship,
        Integer theory,
        Integer duration


) {
}
