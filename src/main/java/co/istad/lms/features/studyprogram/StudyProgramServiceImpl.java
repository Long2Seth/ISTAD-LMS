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

        //validate studyProgram from dto by alias
        if (studyProgramRepository.existsByAlias(studyProgramRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Study program = %s already exists.", studyProgramRequest.alias()));
        }

        //map from DTO to entity
        StudyProgram studyProgram=studyProgramMapper.fromStudyProgramRequest(studyProgramRequest);

        //set isDeleted to false(enable)
        studyProgram.setIsDeleted(false);

        //save to database
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public StudyProgramDetailResponse getStudyProgramByAlias(String alias) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s has not been found.", alias)));

        //map to DTO and return
        return studyProgramMapper.toStudyProgramDetailResponse(studyProgram);
    }

    @Override
    public Page<StudyProgramDetailResponse> getAllStudyPrograms(int page, int size) {

        //crate sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all studyProgram in database
        Page<StudyProgram> studyPrograms = studyProgramRepository.findAll(pageRequest);

        //map entity to DTO and return
        return studyPrograms.map(studyProgramMapper::toStudyProgramDetailResponse);
    }

    @Override
    public StudyProgramResponse updateStudyProgramByAlias(String alias, StudyProgramUpdateRequest studyProgramUpdateRequest) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s was not found.", alias)));

        //map from DTO to entity
        studyProgramMapper.updateStudyProgramFromRequest(studyProgram, studyProgramUpdateRequest);

        //save to database
        studyProgramRepository.save(studyProgram);

        //map entity to DTO and return
        return studyProgramMapper.toStudyProgramResponse(studyProgram);

    }

    @Override
    public void deleteStudyProgramByAlias(String alias) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s was not found.", alias)));

        //delete from database
        studyProgramRepository.delete(studyProgram);

    }

    @Override
    public void enableStudyProgramByAlias(String alias) {

        //validate degree from dto by alias
         StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        studyProgram.setIsDeleted(false);

        //save to database
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public void disableStudyProgramByAlias(String alias) {

        //validate degree from dto by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        studyProgram.setIsDeleted(true);

        //save to database
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public Page<StudyProgramDetailResponse> filterStudyPrograms(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering YearOfStudy entities based on the criteria provided
        Specification<StudyProgram> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<StudyProgram> studyPrograms = studyProgramRepository.findAll(specification,pageRequest);

        //map to DTO and return
        return studyPrograms.map(studyProgramMapper::toStudyProgramDetailResponse);
    }
}
