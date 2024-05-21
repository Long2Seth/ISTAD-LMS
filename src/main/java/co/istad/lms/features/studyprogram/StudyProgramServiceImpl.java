package co.istad.lms.features.studyprogram;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Shift;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.shift.ShiftRepository;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramUpdateRequest;
import co.istad.lms.mapper.StudyProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StudyProgramServiceImpl implements StudyProgramService{

    private final StudyProgramRepository studyProgramRepository;
    private final StudyProgramMapper studyProgramMapper;
    private final BaseSpecification<StudyProgram> baseSpecification;
    @Override
    public void createStudyProgram(StudyProgramRequest studyProgramRequest) {

        if (studyProgramRepository.existsByAlias(studyProgramRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Study program = %s already exists.", studyProgramRequest.alias()));
        }

        StudyProgram studyProgram=studyProgramMapper.fromStudyProgramRequest(studyProgramRequest);
        studyProgram.setIsDeleted(false);
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public StudyProgramDetailResponse getStudyProgramByAlias(String alias) {

        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s was not found.", alias)));

        return studyProgramMapper.toStudyProgramDetailResponse(studyProgram);
    }

    @Override
    public Page<StudyProgramDetailResponse> getAllStudyProgram(int page, int size) {

        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);
        Page<StudyProgram> studyPrograms = studyProgramRepository.findAll(pageRequest);

        return studyPrograms.map(studyProgramMapper::toStudyProgramDetailResponse);
    }

    @Override
    public StudyProgramResponse updateStudyProgramByAlias(String alias, StudyProgramUpdateRequest studyProgramUpdateRequest) {

        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s was not found.", alias)));

        studyProgramMapper.updateStudyProgramFromRequest(studyProgram, studyProgramUpdateRequest);
        studyProgramRepository.save(studyProgram);

        return studyProgramMapper.toStudyProgramResponse(studyProgram);

    }

    @Override
    public void deleteStudyProgramByAlias(String alias) {

        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s was not found.", alias)));

        studyProgramRepository.delete(studyProgram);

    }

    @Override
    public Page<StudyProgramDetailResponse> filterStudyProgram(BaseSpecification.FilterDto filterDto, int page, int size) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        Specification<StudyProgram> specification = baseSpecification.filter(filterDto);

        Page<StudyProgram> studyPrograms = studyProgramRepository.findAll(specification,pageRequest);

        return studyPrograms.map(studyProgramMapper::toStudyProgramDetailResponse);
    }
}
