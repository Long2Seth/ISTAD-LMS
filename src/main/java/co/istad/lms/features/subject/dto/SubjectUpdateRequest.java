package co.istad.lms.features.subject.dto;


import co.istad.lms.domain.StudyProgram;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SubjectUpdateRequest(

        String alias,
        @Size(max = 50, message = "Subject name cannot be longer than 50 characters")
        String subjectName,

        String description,
        String subjectLogo,
        Integer credit,
        Integer duration,

        Set<StudyProgram> studyPrograms


) {
}
