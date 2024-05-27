package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;

import java.util.Set;

public record YearOfStudyResponse(

        String uuid,
        Integer year,
        Integer semester,

        Set<SubjectResponse> subjects

) {
}
