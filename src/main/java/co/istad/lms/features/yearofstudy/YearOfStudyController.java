package co.istad.lms.features.yearofstudy;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramUpdateRequest;
import co.istad.lms.features.yearofstudy.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/year-of-studies")
public class YearOfStudyController {

    private final YearOfStudyService yearOfStudyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('faculty:write')")
    public void createYearOfStudy(@Valid @RequestBody YearOfStudyRequest yearOfStudyRequest) {

        yearOfStudyService.createYearOfStudy(yearOfStudyRequest);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public YearOfStudyDetailResponse getYearOfStudyByAlias(@PathVariable String uuid) {
        return yearOfStudyService.getYearOfStudyByUuid(uuid);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<YearOfStudyDetailResponse> getAllYearOfStudies(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return yearOfStudyService.getAllYearOfStudies(pageNumber, pageSize);

    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public YearOfStudyDetailResponse updateYearOfStudy(
            @PathVariable String uuid,
            @Valid @RequestBody YearOfStudyUpdateRequest yearOfStudyUpdateRequest) {

        return yearOfStudyService.updateYearOfStudyByUuid(uuid, yearOfStudyUpdateRequest);

    }


    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:delete')")
    public void deleteYearOfStudy(@PathVariable String uuid) {

        yearOfStudyService.deleteYearOfStudyByUuid(uuid);

    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<YearOfStudyDetailResponse> filterYearOfStudies(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return yearOfStudyService.filterYearOfStudy(filterDto, pageNumber, pageSize);
    }

    @PostMapping("/{uuid}/subjects")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public YearOfStudyDetailResponse addSubjectToYearOfStudy(
            @PathVariable String uuid,
            @Valid @RequestBody YearOfStudySubjectRequest yearOfStudySubjectRequest) {

        return yearOfStudyService.addSubject(uuid, yearOfStudySubjectRequest);

    }

    @DeleteMapping("/{uuid}/subjects/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:delete')")
    public void deleteYearOfStudySubject(
            @PathVariable String uuid,
            @PathVariable String alias) {

        yearOfStudyService.deleteSubject(uuid, alias);

    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public void disableYearOfStudy(@PathVariable String uuid){

        yearOfStudyService.disableYearOfStudyByUuid(uuid);

    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public void enableYearOfStudy(@PathVariable String uuid){

        yearOfStudyService.enableYearOfStudyByUuid(uuid);

    }

    @PutMapping("/{uuid}/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public void publicYearOfStudy(@PathVariable String uuid){

        yearOfStudyService.publicYearOfStudyByUuid(uuid);

    }

    @PutMapping("/{uuid}/draft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public void draftYearOfStudy(@PathVariable String uuid){

        yearOfStudyService.draftYearOfStudyByUuid(uuid);

    }
}
