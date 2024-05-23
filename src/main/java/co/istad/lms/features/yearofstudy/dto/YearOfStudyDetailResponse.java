package co.istad.lms.features.yearofstudy.dto;

import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

public record YearOfStudyDetailResponse(

        String uuid,
        Integer year,
        Integer semester,
        StudyProgramDetailResponse studyProgram,
        String subjectAlias


) {
}
