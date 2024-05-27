package co.istad.lms.features.course.dto;

public record CourseDetailResponse(

        String alias,
        Integer status,
        Boolean isDeleted,
        String subjectAlias,
        String instructorAlias,
        String classAlias
) {
}
