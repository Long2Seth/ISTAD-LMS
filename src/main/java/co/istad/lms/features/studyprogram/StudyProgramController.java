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
    ResponseEntity<Void> createNewStudyProgram(@Valid @RequestBody StudyProgramRequest studyProgramRequest) {

        studyProgramService.createStudyProgram(studyProgramRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{alias}")
    ResponseEntity<StudyProgramDetailResponse> getStudyProgramByAlias(@PathVariable String alias) {

        StudyProgramDetailResponse studyProgramDetailResponse = studyProgramService.getStudyProgramByAlias(alias);

        return ResponseEntity.ok(studyProgramDetailResponse);
    }

    @GetMapping
    public ResponseEntity<Page<StudyProgramDetailResponse>> getAllShift(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<StudyProgramDetailResponse> studyProgramPage = studyProgramService.getAllStudyProgram(page, size);

        return ResponseEntity.ok(studyProgramPage);
    }

    @PutMapping("/{alias}")
    public ResponseEntity<StudyProgramResponse> updateStudyProgram(@PathVariable String alias,
                                                                   @RequestBody StudyProgramUpdateRequest studyProgramUpdateRequest) {

        StudyProgramResponse shiftUpdateResponse = studyProgramService.updateStudyProgramByAlias(alias, studyProgramUpdateRequest);

        return ResponseEntity.ok(shiftUpdateResponse);
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> deleteStudyProgram(@PathVariable String alias) {

        studyProgramService.deleteStudyProgramByAlias(alias);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public Page<StudyProgramDetailResponse> filterStudyPrograms(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return studyProgramService.filterStudyProgram(filterDto,page,size);
    }

}
