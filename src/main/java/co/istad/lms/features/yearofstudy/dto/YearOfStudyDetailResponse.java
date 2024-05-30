package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

public record YearOfStudyDetailResponse(

        String uuid,
        Integer year,
        Integer semester,
        StudyProgramResponse studyProgram,
        Boolean isDeleted,
        Boolean isDraft,

        Set<SubjectResponse> subjects

) {
}
