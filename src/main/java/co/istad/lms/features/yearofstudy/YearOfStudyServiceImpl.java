package co.istad.lms.features.yearofstudy;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.Subject;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.subject.SubjectRepository;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudySubjectRequest;
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

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class YearOfStudyServiceImpl implements YearOfStudyService {

    private final YearOfStudyRepository yearOfStudyRepository;

    private final YearOfStudyMapper yearOfStudyMapper;

    private final StudyProgramRepository studyProgramRepository;

    private final SubjectRepository subjectRepository;

    private final BaseSpecification<YearOfStudy> baseSpecification;


    @Override
    public void createYearOfStudy(YearOfStudyRequest yearOfStudyRequest) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(yearOfStudyRequest.studyProgramAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("studyProgram = %s has not been found", yearOfStudyRequest.studyProgramAlias())));

        //check year/semester/studyProgram for each yearOfStudy
        //example year:1,semester:1,studyProgram:dev-op, it can not the same all three field of other request
        boolean exists = yearOfStudyRepository.findByYearAndSemesterAndStudyProgram(yearOfStudyRequest.year(), yearOfStudyRequest.semester(), studyProgram).isPresent();

        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("YearOfStudy that studyProgramAlias = %s, year = %d, semester = %d has already existed", yearOfStudyRequest.studyProgramAlias(), yearOfStudyRequest.year(), yearOfStudyRequest.semester()));
        }


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
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("YearOfStudy = %s has not been found.", uuid)));

        //map to DTO and return
        return yearOfStudyMapper.toYearOfStudyDetailResponse(yearOfStudy);
    }

    @Override
    public Page<YearOfStudyDetailResponse> getAllYearOfStudies(int pageNumber, int pageSize) {

        //crate sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all yearOfStudy in database
        Page<YearOfStudy> yearOfStudies = yearOfStudyRepository.findAll(pageRequest);

        //map entity to DTO and return
        return yearOfStudies.map(yearOfStudyMapper::toYearOfStudyDetailResponse);
    }

    @Override
    public YearOfStudyDetailResponse updateYearOfStudyByUuid(String uuid, YearOfStudyUpdateRequest yearOfStudyUpdateRequest) {

        //validate yearOfStudy from DTO by alias
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("yearOfStudy = %s was not found.", uuid)));

        //map from DTO to entity
        yearOfStudyMapper.updateYearOfStudyFromRequest(yearOfStudy, yearOfStudyUpdateRequest);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);

        //map entity to DTO and return
        return yearOfStudyMapper.toYearOfStudyDetailResponse(yearOfStudy);
    }

    @Override
    public void deleteYearOfStudyByUuid(String uuid) {

        //validate yearOfStudy from DTO by alias
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s was not found.", uuid)));

        //delete from database
        yearOfStudyRepository.delete(yearOfStudy);
    }

    @Override
    public Page<YearOfStudyDetailResponse> filterYearOfStudy(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering YearOfStudy entities based on the criteria provided
        Specification<YearOfStudy> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<YearOfStudy> yearOfStudies = yearOfStudyRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return yearOfStudies.map(yearOfStudyMapper::toYearOfStudyDetailResponse);

    }

    @Override
    public YearOfStudyDetailResponse addSubject(String uuid, YearOfStudySubjectRequest yearOfStudySubjectRequest) {

        //validate year of study from dto by uuid
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("year of study = %s has been not found", uuid)));

        //validate subject from dto by alias
        Set<Subject> requestedSubjects = yearOfStudySubjectRequest.aliasOfSubjects().stream().map(subjectAlias -> subjectRepository.findByAlias(subjectAlias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found", subjectAlias)))).collect(Collectors.toSet());

        // get all subject in yearOfStudy
        Set<Subject> allSubjects = yearOfStudy.getSubjects();

        // If the existing subjects set is modifiable, directly add all requested subjects
        allSubjects.addAll(requestedSubjects);

        //set subject to yearOfStudy
        yearOfStudy.setSubjects(allSubjects);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);

        return yearOfStudyMapper.toYearOfStudyDetailResponse(yearOfStudy);
    }

    @Override
    public void deleteSubject(String uuid, String alias) {

        //validate year of study from dto by uuid
        YearOfStudy yearOfStudy = yearOfStudyRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("year of study = %s has been not found", uuid)));

        //find subject by alias from dto
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                , String.format("Subject  = %s has not been found", alias)));

        //get all subject from yearOfStudy
        Set<Subject> allSubjects = yearOfStudy.getSubjects();

        // Check if the subject exists in the yearOfStudy
        if (allSubjects == null || !allSubjects.contains(subject)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not existed in YearOfStudy = %s", alias, uuid));
        }


        //remove a subject
        allSubjects.remove(subject);

        //set new Set of subject to entity after remove
        yearOfStudy.setSubjects(allSubjects);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);

    }

    @Override
    public void enableYearOfStudyByUuid(String uuid) {

        //get yearOfStudy by uuid from DTO
        YearOfStudy yearOfStudy=
                yearOfStudyRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("YearOfStud = %s has not been found",uuid)));

        //set isDeleted to false(enable)
        yearOfStudy.setIsDeleted(false);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);

    }

    @Override
    public void disableYearOfStudyByUuid(String uuid) {

        //get yearOfStudy by uuid from DTO
        YearOfStudy yearOfStudy=
                yearOfStudyRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("YearOfStud = %s has not been found",uuid)));

        //set isDeleted to false(disable)
        yearOfStudy.setIsDeleted(true);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);

    }

    @Override
    public void publicYearOfStudyByUuid(String uuid) {

        //get yearOfStudy by uuid from DTO
        YearOfStudy yearOfStudy=
                yearOfStudyRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("YearOfStud = %s has not been found",uuid)));

        //set isDraft to false(public)
        yearOfStudy.setIsDraft(false);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);
    }

    @Override
    public void draftYearOfStudyByUuid(String uuid) {

        //get yearOfStudy by uuid from DTO
        YearOfStudy yearOfStudy=
                yearOfStudyRepository.findByUuid(uuid).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("YearOfStud = %s has not been found",uuid)));

        //set isDraft to false(draft)
        yearOfStudy.setIsDeleted(true);

        //save to database
        yearOfStudyRepository.save(yearOfStudy);
    }
}
