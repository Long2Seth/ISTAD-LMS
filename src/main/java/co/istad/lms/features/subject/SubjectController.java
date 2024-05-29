package co.istad.lms.features.subject;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.subject.dto.SubjectDetailResponse;
import co.istad.lms.features.subject.dto.SubjectRequest;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.subject.dto.SubjectUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void creatSubject(@Valid @RequestBody SubjectRequest subjectRequest) {

        subjectService.createSubject(subjectRequest);

    }

    @GetMapping("/{alias}")
    SubjectDetailResponse getSubjectByAlias(@PathVariable String alias) {

        return subjectService.getSubjectByAlias(alias);

    }

    @GetMapping
    public Page<SubjectDetailResponse> getAllSubjects(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return subjectService.getAllSubject(page, size);
    }

    @PutMapping("/{alias}")
    public SubjectResponse updateSubject(@PathVariable String alias,
                                        @Valid @RequestBody SubjectUpdateRequest subjectUpdateRequest) {

        return subjectService.updateSubjectByAlias(alias, subjectUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable String alias) {

        subjectService.deleteSubjectByAlias(alias);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableSubject(@PathVariable String alias){

        subjectService.enableSubjectByAlias(alias);

    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableSubject(@PathVariable String alias){

        subjectService.disableSubjectByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<SubjectDetailResponse> filterSubjects(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return subjectService.filterSubject(filterDto, page, size);
    }

}
