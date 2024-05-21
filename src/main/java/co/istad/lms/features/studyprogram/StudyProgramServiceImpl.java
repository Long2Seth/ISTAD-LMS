package co.istad.lms.features.studyprogram;

import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyProgramServiceImpl implements StudyProgramService{
    @Override
    public void createStudyProgram(StudyProgramRequest studyProgramRequest) {

    }

    @Override
    public StudyProgramDetailResponse getStudyProgramByAlias(String alias) {
        return null;
    }

    @Override
    public StudyProgramDetailResponse getAllStudyProgram(int page, int size) {
        return null;
    }

    @Override
    public void deleteStudyProgramByAlias(String alias) {

    }
}
