package co.istad.lms.features.classes.dto;

import co.istad.lms.domain.Generation;
import co.istad.lms.domain.Shift;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.generation.dto.GenerationResponse;
import co.istad.lms.features.student.dto.StudentResponse;

import java.time.LocalDate;
import java.util.Set;

    public record ClassResponse(

            String alias,
            String className,
            Set<StudentResponse> students
    ) {
    }
