package co.istad.lms.features.subject;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.Subject;
import co.istad.lms.features.minio.MinioStorageService;
import co.istad.lms.features.studyprogram.StudyProgramRepository;
import co.istad.lms.features.subject.dto.SubjectDetailResponse;
import co.istad.lms.features.subject.dto.SubjectRequest;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.subject.dto.SubjectUpdateRequest;
import co.istad.lms.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.MalformedInputException;
import java.util.Set;
import java.util.stream.Collectors;

import static co.istad.lms.utils.MediaUtil.getUrl;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper subjectMapper;

    private final SubjectRepository subjectRepository;

    private final MinioStorageService minioStorageService;

    private final BaseSpecification<Subject> baseSpecification;


    @Override
    public void createSubject(SubjectRequest subjectRequest) {

        //validate subject by alias
        if (subjectRepository.existsByAlias(subjectRequest.alias())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Subject = %s already exists.", subjectRequest.alias()));
        }


        // map DTO to entity
        Subject subject = subjectMapper.fromDegreeRequest(subjectRequest);


        // Save the subject entity
        subjectRepository.save(subject);


    }

    @Override
    public SubjectDetailResponse getSubjectByAlias(String alias) {

        //find subject by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found.", alias)));

        //set logo url to faculty
        if (subject.getLogo() != null) {
            subject.setLogo(getUrl(subject.getLogo(), minioStorageService));
        }

        //return subject detail
        return subjectMapper.toSubjectDetailResponse(subject);

    }

    @Override
    public Page<SubjectDetailResponse> getAllSubject(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //find all subject in database
        Page<Subject> subjects = subjectRepository.findAll(pageRequest);

        //set logo url to faculty
        subjects.forEach(subject -> {
            if (subject.getLogo() != null) {
                subject.setLogo(getUrl(subject.getLogo(), minioStorageService));
            }
        });

        //map entity to DTO and return
        return subjects.map(subjectMapper::toSubjectDetailResponse);

    }

    @Override
    public SubjectDetailResponse updateSubjectByAlias(String alias, SubjectUpdateRequest subjectUpdateRequest) {

        //find subject by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found.", alias)));

        //check null alias from DTO
        if (subjectUpdateRequest.alias() != null) {

            //validate alias from dto with original alias
            if (!alias.equalsIgnoreCase(subjectUpdateRequest.alias())) {

                //validate new alias is conflict with other alias or not
                if (subjectRepository.existsByAlias(subjectUpdateRequest.alias())) {

                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            String.format("Subject = %s already exist.", subjectUpdateRequest.alias()));
                }
            }
        }

        //map DTO to entity
        subjectMapper.updateSubjectFromRequest(subject, subjectUpdateRequest);

        //save to database
        subjectRepository.save(subject);

        //set logo url to faculty
        if (subject.getLogo() != null) {
            subject.setLogo(getUrl(subject.getLogo(), minioStorageService));
        }

        //return Subject response
        return subjectMapper.toSubjectDetailResponse(subject);

    }

    @Override
    public void deleteSubjectByAlias(String alias) {

        //find subject in database by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found.", alias)));

        //delete subject in database
        subjectRepository.delete(subject);

    }

    @Override
    public void enableSubjectByAlias(String alias) {

        //validate subject from dto by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found ! ", alias)));

        //set isDeleted to false(enable)
        subject.setIsDeleted(false);
        //save to database
        subjectRepository.save(subject);

    }

    @Override
    public void disableSubjectByAlias(String alias) {

        //validate subject from dto by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found ! ", alias)));

        //set isDeleted to true(disable)
        subject.setIsDeleted(true);

        //save to database
        subjectRepository.save(subject);

    }

    @Override
    public void publicSubjectByAlias(String alias) {

        //validate subject from dto by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found ! ", alias)));

        //set isDraft to false(public)
        subject.setIsDraft(false);

        //save to database
        subjectRepository.save(subject);
    }

    @Override
    public void draftSubjectByAlias(String alias) {

        //validate subject from dto by alias
        Subject subject = subjectRepository.findByAlias(alias).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subject = %s has not been found ! ", alias)));

        //set isDraft to true(private)
        subject.setIsDraft(true);

        //save to database
        subjectRepository.save(subject);
    }

    @Override
    public Page<SubjectDetailResponse> filterSubject(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        //create a dynamic query specification for filtering Subject entities based on the criteria provided
        Specification<Subject> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Subject> subjects = subjectRepository.findAll(specification, pageRequest);

        //set logo url to faculty
        subjects.forEach(subject -> {
            if (subject.getLogo() != null) {
                subject.setLogo(getUrl(subject.getLogo(), minioStorageService));
            }
        });
        //map to DTO and return
        return subjects.map(subjectMapper::toSubjectDetailResponse);

    }
}
