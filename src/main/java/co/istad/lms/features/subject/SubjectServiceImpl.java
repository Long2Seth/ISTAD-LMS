package co.istad.lms.features.subject;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.Subject;
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

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper subjectMapper;

    private final SubjectRepository subjectRepository;

    private final BaseSpecification<Subject> baseSpecification;

    private final StudyProgramRepository studyProgramRepository;

    @Override
    public void createSubject(SubjectRequest subjectRequest) {

        //validate subject by alias
        if (subjectRepository.existsByAlias(subjectRequest.alias())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Subject = %s already exists.", subjectRequest.alias()));
        }

        // Fetch studyProgram by their alias from the request
        Set<StudyProgram> studyPrograms = subjectRequest.studyProgramAlias().stream()

                .map(studyProgramAlias -> studyProgramRepository.findAllByAlias(studyProgramAlias)

                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("StudyProgram with Alias = %s has not been found.", studyProgramAlias))))
                .collect(Collectors.toSet());


        // map DTO to entity
        Subject subject = subjectMapper.fromDegreeRequest(subjectRequest);

        // Set studyPrograms to the subject entity
        subject.setStudyPrograms(studyPrograms);

        // Add the subject to each studyProgram's subjects set
        studyPrograms.forEach(studyProgram -> {

            studyProgram.getSubjects().add(subject);

            // Save the updated studyProgram
            studyProgramRepository.save(studyProgram);
        });

        // Save the subject entity
        subjectRepository.save(subject);


    }

    @Override
    public SubjectDetailResponse getSubjectByAlias(String alias) {

        //find subject by alias
        Subject subject = subjectRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Subject = %s has not been found.", alias)));

        //return subject detail
        return subjectMapper.toSubjectDetailResponse(subject);

    }

    @Override
    public Page<SubjectDetailResponse> getAllSubject(int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //find all subject in database
        Page<Subject> subject = subjectRepository.findAll(pageRequest);

        //map entity to DTO and return
        return subject.map(subjectMapper::toSubjectDetailResponse);

    }

    @Override
    public SubjectResponse updateSubjectByAlias(String alias, SubjectUpdateRequest subjectUpdateRequest) {

        //find subject by alias
        Subject subject = subjectRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Subject = %s has not been found.", alias)));

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

        //return Subject response
        return subjectMapper.toSubjectResponse(subject);

    }

    @Override
    public void deleteSubjectByAlias(String alias) {

        //find degree in database by alias
        Subject subject = subjectRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Subject = %s has not been found.", alias)));

        //delete subject in database
        subjectRepository.delete(subject);

    }

    @Override
    public void enableSubjectByAlias(String alias) {

        //validate subject from dto by alias
        Subject subject = subjectRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Subject = %s has not been found ! ", alias)));

        //save to database
        subjectRepository.save(subject);

    }

    @Override
    public void disableSubjectByAlias(String alias) {

        //validate degree from dto by alias
        Subject subject = subjectRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Subject = %s has not been found ! ", alias)));

        //save to database
        subjectRepository.save(subject);

    }

    @Override
    public Page<SubjectDetailResponse> filterSubject(BaseSpecification.FilterDto filterDto, int page, int size) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdAt");

        //create pagination with current page and size of page
        PageRequest pageRequest = PageRequest.of(page, size, sortById);

        //create a dynamic query specification for filtering Degree entities based on the criteria provided
        Specification<Subject> specification = baseSpecification.filter(filterDto);

        //get all entity that match with filter condition
        Page<Subject> subjects = subjectRepository.findAll(specification, pageRequest);

        //map to DTO and return
        return subjects.map(subjectMapper::toSubjectDetailResponse);

    }
}
