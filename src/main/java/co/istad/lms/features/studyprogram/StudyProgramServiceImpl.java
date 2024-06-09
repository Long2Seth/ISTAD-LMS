package co.istad.lms.features.studyprogram;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Faculty;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.degree.DegreeRepository;
import co.istad.lms.features.faculties.FacultyRepository;
import co.istad.lms.features.minio.MinioStorageService;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramUpdateRequest;
import co.istad.lms.features.yearofstudy.YearOfStudyRepository;
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
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository studyProgramRepository;

    private final StudyProgramMapper studyProgramMapper;

    private final BaseSpecification<StudyProgram> baseSpecification;

    private final DegreeRepository degreeRepository;

    private final FacultyRepository facultyRepository;

    private final MinioStorageService minioStorageService;


    @Override
    public void createStudyProgram(StudyProgramRequest studyProgramRequest) {

        //validate studyProgram from dto by alias
        if (studyProgramRepository.existsByAlias(studyProgramRequest.alias())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Study program = %s has already existed.", studyProgramRequest.alias()));
        }

        //validate logo from DTO
        if (studyProgramRequest.logo() != null && !studyProgramRequest.logo().trim().isEmpty() && !minioStorageService.doesObjectExist(studyProgramRequest.logo())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Logo = %s has not been found",
                    studyProgramRequest.logo()));
        }

        //validate degree by alias from DTO
        Degree degree =
                degreeRepository.findByAliasAndIsDeletedFalse(studyProgramRequest.degreeAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("degree = %s has not been found.", studyProgramRequest.degreeAlias())));

        //validate faculty by alias from DTO
        Faculty faculty =
                facultyRepository.findByAliasAndIsDeletedFalse(studyProgramRequest.facultyAlias()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Faculty = %s has not been found.", studyProgramRequest.facultyAlias())));


        //map from DTO to entity
        StudyProgram studyProgram = studyProgramMapper.fromStudyProgramRequest(studyProgramRequest);

        //set isDeleted to false(enable)
        studyProgram.setIsDeleted(false);

        //set degree
        studyProgram.setDegree(degree);

        //set faculty
        studyProgram.setFaculty(faculty);


        //save to database
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public StudyProgramDetailResponse getStudyProgramByAlias(String alias) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s has not been found.", alias)));

        //update logo url for studyProgram
        if (studyProgram.getLogo() != null && !studyProgram.getLogo().trim().isEmpty()) {
            studyProgram.setLogo(minioStorageService.getUrl(studyProgram.getLogo()));
        }

        //map to DTO and return
        return studyProgramMapper.toStudyProgramDetailResponse(studyProgram);
    }

    @Override
    public Page<StudyProgramDetailResponse> getAllStudyPrograms(int pageNumber, int pageSize) {

        //crate sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all studyProgram in database
        Page<StudyProgram> studyPrograms = studyProgramRepository.findAll(pageRequest);

        // update the logo URL for each studyProgram
        studyPrograms.forEach(studyProgram -> {
            if (studyProgram.getLogo() != null && !studyProgram.getLogo().trim().isEmpty()) {
                studyProgram.setLogo(minioStorageService.getUrl(studyProgram.getLogo()));
            }
        });
        //map entity to DTO and return
        return studyPrograms.map(studyProgramMapper::toStudyProgramDetailResponse);
    }

    @Override
    public StudyProgramDetailResponse updateStudyProgramByAlias(String alias, StudyProgramUpdateRequest studyProgramUpdateRequest) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s was not found.", alias)));

        //check null alias from DTO
        if (studyProgramUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(studyProgramUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (studyProgramRepository.existsByAlias(studyProgramUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("StudyProgram = %s already exist.", studyProgramUpdateRequest.alias()));
                }
            }
        }


        //map from DTO to entity
        studyProgramMapper.updateStudyProgramFromRequest(studyProgram, studyProgramUpdateRequest);

        //update logo url for studyProgram
        if (studyProgramUpdateRequest.logo() != null && !studyProgramUpdateRequest.logo().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Logo = %s has not been found",
                    studyProgramUpdateRequest.logo()));
        }

        //save to database
        studyProgramRepository.save(studyProgram);

        //set url to logo
        studyProgram.setLogo(minioStorageService.getUrl(studyProgram.getLogo()));

        //map entity to DTO and return
        return studyProgramMapper.toStudyProgramDetailResponse(studyProgram);

    }

    @Override
    public void deleteStudyProgramByAlias(String alias) {

        //validate studyProgram from DTO by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s was not found.", alias)));

        //delete from database
        studyProgramRepository.delete(studyProgram);

    }

    @Override
    public void enableStudyProgramByAlias(String alias) {

        //validate degree from dto by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        studyProgram.setIsDeleted(false);

        //save to database
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public void disableStudyProgramByAlias(String alias) {

        //validate degree from dto by alias
        StudyProgram studyProgram = studyProgramRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        studyProgram.setIsDeleted(true);

        //set isDraft to true(private)
        studyProgram.setIsDraft(true);

        //save to database
        studyProgramRepository.save(studyProgram);

    }

    @Override
    public void publicStudyProgramByAlias(String alias) {

        //validate degree from dto by alias
        StudyProgram studyProgram =
                studyProgramRepository.findByAliasAndIsDeletedFalse(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s has not been found ! ", alias)));

        //set isDraft to false(public)
        studyProgram.setIsDraft(false);

        //save to database
        studyProgramRepository.save(studyProgram);
    }

    @Override
    public void draftStudyProgramByAlias(String alias) {

        //validate degree from dto by alias
        StudyProgram studyProgram =
                studyProgramRepository.findByAliasAndIsDeletedFalse(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Study program = %s has not been found ! ", alias)));

        //set isDraft to true(private)
        studyProgram.setIsDraft(true);

        //save to database
        studyProgramRepository.save(studyProgram);
    }

    @Override
    public Page<StudyProgramDetailResponse> filterStudyPrograms(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering YearOfStudy entities based on the criteria provided
        Specification<StudyProgram> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<StudyProgram> studyPrograms = studyProgramRepository.findAll(specification, pageRequest);

        // update the logo URL for each studyProgram
        studyPrograms.forEach(studyProgram -> {
            if (studyProgram.getLogo() != null && !studyProgram.getLogo().trim().isEmpty()) {
                studyProgram.setLogo(minioStorageService.getUrl(studyProgram.getLogo()));
            }
        });

        //map to DTO and return
        return studyPrograms.map(studyProgramMapper::toStudyProgramDetailResponse);
    }
}
