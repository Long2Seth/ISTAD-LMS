package co.istad.lms.features.course.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;

import java.util.Set;

public record CourseResponse(


        String title,
        Double score,
        Integer credit,
        String grade



) {
}
