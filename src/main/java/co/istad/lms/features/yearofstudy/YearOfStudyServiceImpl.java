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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    private final BaseSpecification<YearOfStudy> baseSpecification;


    @Override
    public void createYearOfStudy(YearOfStudyRequest yearOfStudyRequest) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(yearOfStudyRequest.studyProgramAlias())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("studyProgram = %s has not been found", yearOfStudyRequest.studyProgramAlias())));

        //map from DTO to entity
        YearOfStudy yearOfStudy = yearOfStudyMapper.fromYearOfStudyRequest(yearOfStudyRequest);

        //random uuid for year of study
        yearOfStudy.setUuid(UUID.randomUUID().toString());

        //set studyProgram to yearOfStudy
        yearOfStudy.setStudyProgram(studyProgram);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);
    }

    @Override
    public YearOfStudyDetailResponse getYearOfStudyByUuid(String uuid) {

        //validate yearOfStudy from DTO by uuid
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("YearOfStudy = %s has not been found.", uuid)));

        //map to DTO and return
        return yearOfStudyMapper.toYearOfStudyDetailResponse(yearOfStudy);
    }

    @Override
    public Page<YearOfStudyDetailResponse> getAllYearOfStudies(int page, int size) {

        //crate sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all yearOfStudy in database
        Page<YearOfStudy> yearOfStudies = yearOfStudyRepository.findAll(pageRequest);

        //map entity to DTO and return
        return yearOfStudies.map(yearOfStudyMapper::toYearOfStudyDetailResponse);
    }

    @Override
    public YearOfStudyResponse updateYearOfStudyByUuid(String uuid, YearOfStudyUpdateRequest yearOfStudyUpdateRequest) {

        //validate yearOfStudy from DTO by alias
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("yearOfStudy = %s was not found.", uuid)));

        //map from DTO to entity
        yearOfStudyMapper.updateYearOfStudyFromRequest(yearOfStudy, yearOfStudyUpdateRequest);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);

        //map entity to DTO and return
        return yearOfStudyMapper.toYearOfStudyResponse(yearOfStudy);
    }

    @Override
    public void deleteYearOfStudyByUuid(String uuid) {

        //validate yearOfStudy from DTO by alias
        YearOfStudy yearOfStudy=yearOfStudyRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Study program = %s was not found.", uuid)));

        //delete from database
        yearOfStudyRepository.delete(yearOfStudy);
    }

    @Override
    public Page<YearOfStudyDetailResponse> filterYearOfStudy(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering YearOfStudy entities based on the criteria provided
        Specification<YearOfStudy> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<YearOfStudy> yearOfStudies = yearOfStudyRepository.findAll(specification,pageRequest);

        //map to DTO and return
        return yearOfStudies.map(yearOfStudyMapper::toYearOfStudyDetailResponse);

    }
}
