package co.istad.lms.features.course.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;

public record CourseDetailResponse(

        String alias,
        Integer status,
        Boolean isDeleted,
        SubjectResponse subject,
        InstructorResponse instructor,
        ClassResponse oneClass
) {
}
