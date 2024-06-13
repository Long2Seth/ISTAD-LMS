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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('faculty:write')")
    void creatSubject(@Valid @RequestBody SubjectRequest subjectRequest) {

        subjectService.createSubject(subjectRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    SubjectDetailResponse getSubjectByAlias(@PathVariable String alias) {

        return subjectService.getSubjectByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<SubjectDetailResponse> getAllSubjects(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return subjectService.getAllSubject(pageNumber, pageSize);
    }

    @PatchMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public SubjectDetailResponse updateSubject(@PathVariable String alias,
                                        @Valid @RequestBody SubjectUpdateRequest subjectUpdateRequest) {

        return subjectService.updateSubjectByAlias(alias, subjectUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:delete')")
    public void deleteSubject(@PathVariable String alias) {

        subjectService.deleteSubjectByAlias(alias);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void enableSubject(@PathVariable String alias){

        subjectService.enableSubjectByAlias(alias);

    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void disableSubject(@PathVariable String alias){

        subjectService.disableSubjectByAlias(alias);
    }

    @PutMapping("/{alias}/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void publicSubject(@PathVariable String alias){

        subjectService.publicSubjectByAlias(alias);
    }

    @PutMapping("/{alias}/draft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void draftSubject(@PathVariable String alias){

        subjectService.draftSubjectByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<SubjectDetailResponse> filterSubjects(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return subjectService.filterSubject(filterDto, pageNumber, pageSize);
    }

}
