package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;

import java.util.Set;

public record YearOfStudySubjectResponse(
        String uuid,
        Integer year,
        Integer semester,
        StudyProgramResponse studyProgram,
        Boolean isDeleted,
        Boolean isDraft
) {
}
