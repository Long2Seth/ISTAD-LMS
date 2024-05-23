package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;

public record YearOfStudyResponse(

        String uuid,
        Integer year,
        Integer semester,
        StudyProgramDetailResponse studyProgram,
        String subjectAlias
) {
}
