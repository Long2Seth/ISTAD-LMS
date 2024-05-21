package co.istad.lms.features.yearofstudy;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.*;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyUpdateRequest;
import co.istad.lms.mapper.YearOfStudyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class YearOfStudyServiceImpl implements YearOfStudyService{

    private final YearOfStudyRepository yearOfStudyRepository;

    private  final YearOfStudyMapper yearOfStudyMapper;

    private final StudyProgramRepository studyProgramRepository;

    @Override
    public void createNewYearOfStudy(YearOfStudyRequest yearOfStudyRequest) {

        StudyProgram studyProgram = studyProgramRepository.findByAlias(yearOfStudyRequest.studyProgramAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("study program = %s was not found", yearOfStudyRequest.studyProgramAlias())));

        YearOfStudy yearOfStudy = yearOfStudyMapper.fromYearOfStudyRequest(yearOfStudyRequest);
        yearOfStudy.setUuid(UUID.randomUUID().toString()); // Generate UUID
        yearOfStudyRepository.save(yearOfStudy);
    }

    @Override
    public YearOfStudyDetailResponse getYearOfStudyByAlias(String alias) {
        return null;
    }

    @Override
    public Page<YearOfStudyDetailResponse> getAllYearOfStudy(int page, int size) {
        return null;
    }

    @Override
    public YearOfStudyResponse updateYearOfStudyByAlias(String alias, YearOfStudyUpdateRequest yearOfStudyUpdateRequest) {
        return null;
    }

    @Override
    public void deleteYearOfStudyByAlias(String alias) {

    }

    @Override
    public Page<YearOfStudyDetailResponse> filterYearOfStudy(BaseSpecification.FilterDto filterDto, int page, int size) {
        return null;
    }
}
