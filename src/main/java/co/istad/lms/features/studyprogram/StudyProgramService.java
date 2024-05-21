package co.istad.lms.features.studyprogram;

import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface StudyProgramService {

    void createStudyProgram(StudyProgramRequest studyProgramRequest);

    StudyProgramDetailResponse getStudyProgramByAlias(String alias);

    StudyProgramDetailResponse getAllStudyProgram(int page, int size);

    void deleteStudyProgramByAlias(String alias);
}
