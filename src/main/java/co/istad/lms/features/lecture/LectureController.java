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

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('session:read')")
    LectureDetailResponse getLectureByUuid(@PathVariable String uuid) {

        return lectureService.getLectureByUuid(uuid);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('session:read')")
    public Page<LectureDetailResponse> getAllLectures(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return lectureService.getAllLectures(pageNumber, pageSize);
    }


    @PatchMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('session:update')")
    public LectureDetailResponse updateLecture(@PathVariable String uuid,
                                               @Valid @RequestBody LectureUpdateRequest lectureUpdateRequest) {

        return lectureService.updateLectureByUuid(uuid, lectureUpdateRequest);
    }


    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('session:delete')")
    public void deleteLecture(@PathVariable String uuid) {

        lectureService.deleteLectureByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('session:update')")
    void enableLecture(@PathVariable String uuid) {

        lectureService.enableLectureByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('session:update')")
    void disableLecture(@PathVariable String uuid) {

        lectureService.disableLectureByUuid(uuid);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('session:read')")
    public Page<LectureDetailResponse> filterLectures(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return lectureService.filterLectures(filterDto, pageNumber, pageSize);
    }

}
