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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/year-of-studies")
public class YearOfStudyController {

    private final YearOfStudyService yearOfStudyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createYearOfStudy(@Valid @RequestBody YearOfStudyRequest yearOfStudyRequest) {

        yearOfStudyService.createYearOfStudy(yearOfStudyRequest);
    }

    @GetMapping("/{uuid}")
    public YearOfStudyDetailResponse getYearOfStudyByAlias(@PathVariable String uuid) {
        return yearOfStudyService.getYearOfStudyByUuid(uuid);
    }

    @GetMapping
    public Page<YearOfStudyDetailResponse> getAllYearOfStudies(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return yearOfStudyService.getAllYearOfStudies(page, size);

    }

    @PutMapping("/{uuid}")
    public YearOfStudyResponse updateYearOfStudy(
            @PathVariable String uuid,
            @Valid @RequestBody YearOfStudyUpdateRequest yearOfStudyUpdateRequest) {

        return yearOfStudyService.updateYearOfStudyByUuid(uuid, yearOfStudyUpdateRequest);

    }


    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteYearOfStudy(@PathVariable String uuid) {

        yearOfStudyService.deleteYearOfStudyByUuid(uuid);

    }

    @GetMapping("/filter")
    public Page<YearOfStudyDetailResponse> filterYearOfStudies(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return yearOfStudyService.filterYearOfStudy(filterDto, page, size);
    }

    @PostMapping("/{uuid}/subjects")
    public YearOfStudyDetailResponse updateYearOfStudySubject(
            @PathVariable String uuid,
            @Valid @RequestBody YearOfStudySubjectRequest yearOfStudySubjectRequest) {

        return yearOfStudyService.adSubject(uuid, yearOfStudySubjectRequest);

    }

    @DeleteMapping("/{uuid}/subjects/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteYearOfStudySubject(
            @PathVariable String uuid,
            @PathVariable String alias) {

        yearOfStudyService.deleteSubject(uuid, alias);

    }
}
