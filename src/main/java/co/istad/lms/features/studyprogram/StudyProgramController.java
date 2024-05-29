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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study-programs")
public class StudyProgramController {

    private final StudyProgramService studyProgramService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudyProgram(@Valid @RequestBody StudyProgramRequest studyProgramRequest) {

        studyProgramService.createStudyProgram(studyProgramRequest);

    }

    @GetMapping("/{alias}")
    StudyProgramDetailResponse getStudyProgramByAlias(@PathVariable String alias) {

        return studyProgramService.getStudyProgramByAlias(alias);

    }

    @GetMapping
    public Page<StudyProgramDetailResponse> getAllStudyPrograms(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return studyProgramService.getAllStudyPrograms(page, size);

    }

    @PutMapping("/{alias}")
    public StudyProgramResponse updateStudyProgram(@PathVariable String alias,
                                                   @Valid @RequestBody StudyProgramUpdateRequest studyProgramUpdateRequest) {

        return studyProgramService.updateStudyProgramByAlias(alias, studyProgramUpdateRequest);

    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudyProgram(@PathVariable String alias) {

        studyProgramService.deleteStudyProgramByAlias(alias);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableStudyProgram(@PathVariable String alias) {

        studyProgramService.enableStudyProgramByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableStudyProgram(@PathVariable String alias) {

        studyProgramService.disableStudyProgramByAlias(alias);
    }


    @GetMapping("/filter")
    public Page<StudyProgramDetailResponse> filterStudyPrograms(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return studyProgramService.filterStudyPrograms(filterDto, page, size);
    }

}
