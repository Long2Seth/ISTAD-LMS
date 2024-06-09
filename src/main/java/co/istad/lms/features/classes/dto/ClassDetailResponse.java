package co.istad.lms.features.classes.dto;

import co.istad.lms.domain.Course;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.generation.dto.GenerationResponse;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;

import java.time.LocalDate;
import java.util.Set;

public record ClassDetailResponse(

        String alias,
        String className,
        String description,
        Boolean isDeleted,
        Boolean isDraft,
        InstructorResponse instructor,
        StudyProgramResponse studyProgram,
        ShiftResponse shift,
        GenerationResponse generation,

        Set<StudentResponse> students,

        Set<CourseResponse> courses
) {
}