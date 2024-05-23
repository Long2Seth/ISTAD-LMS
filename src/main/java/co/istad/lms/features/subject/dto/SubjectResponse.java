package co.istad.lms.features.subject.dto;

public record SubjectResponse(

        String alias,
        String subjectName,
        String description,
        String subjectLogo,
        Integer credit,
        Integer duration


) {
}
