package co.istad.lms.features.course.dto;

import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;

import java.time.LocalDate;
import java.util.Set;

public record CourseDetailResponse(

        String uuid,
        Boolean isDeleted,
        SubjectResponse subject,
        InstructorResponse instructor,
        ClassResponse oneClass,
        LocalDate courseStart,

        Integer status,

        LocalDate courseEnd,

        YearOfStudyResponse yearOfStudy
) {
}
