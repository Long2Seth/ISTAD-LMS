package co.istad.lms.features.studyprogram;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study-programs")
public class StudyProgramController {

    private final StudyProgramService studyProgramService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public void createStudyProgram(@Valid @RequestBody StudyProgramRequest studyProgramRequest) {

        studyProgramService.createStudyProgram(studyProgramRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    StudyProgramDetailResponse getStudyProgramByAlias(@PathVariable String alias) {

        return studyProgramService.getStudyProgramByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<StudyProgramDetailResponse> getAllStudyPrograms(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return studyProgramService.getAllStudyPrograms(pageNumber, pageSize);

    }

    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public StudyProgramDetailResponse updateStudyProgram(@PathVariable String alias,
                                                   @Valid @RequestBody StudyProgramUpdateRequest studyProgramUpdateRequest) {

        return studyProgramService.updateStudyProgramByAlias(alias, studyProgramUpdateRequest);

    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public void deleteStudyProgram(@PathVariable String alias) {

        studyProgramService.deleteStudyProgramByAlias(alias);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void enableStudyProgram(@PathVariable String alias) {

        studyProgramService.enableStudyProgramByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void disableStudyProgram(@PathVariable String alias) {

        studyProgramService.disableStudyProgramByAlias(alias);
    }

    @PutMapping("/{alias}/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void publicStudyProgram(@PathVariable String alias) {

        studyProgramService.publicStudyProgramByAlias(alias);
    }

    @PutMapping("/{alias}/draft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void draftStudyProgram(@PathVariable String alias) {

        studyProgramService.draftStudyProgramByAlias(alias);
    }


    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<StudyProgramDetailResponse> filterStudyPrograms(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return studyProgramService.filterStudyPrograms(filterDto, pageNumber, pageSize);
    }

}
