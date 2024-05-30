package co.istad.lms.features.lecture;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('session:write')")
    void createLecture(@Valid @RequestBody LectureRequest lectureRequest) {

        lectureService.createLecture(lectureRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('session:read')")
    LectureDetailResponse getLectureByAlias(@PathVariable String alias) {

        return lectureService.getLectureByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('session:read')")
    public Page<LectureDetailResponse> getAllLectures(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return lectureService.getAllLectures(page, size);
    }


    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('session:update')")
    public LectureDetailResponse updateLecture(@PathVariable String alias,
                                               @Valid @RequestBody LectureUpdateRequest lectureUpdateRequest) {

        return lectureService.updateLectureByAlias(alias, lectureUpdateRequest);
    }


    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('session:delete')")
    public void deleteLecture(@PathVariable String alias) {

        lectureService.deleteLectureByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('session:update')")
    void enableLecture(@PathVariable String alias) {

        lectureService.enableLectureByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('session:update')")
    void disableLecture(@PathVariable String alias) {

        lectureService.disableLectureByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('session:read')")
    public Page<LectureDetailResponse> filterLectures(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return lectureService.filterLectures(filterDto, page, size);
    }

}
