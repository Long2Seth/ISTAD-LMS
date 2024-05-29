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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createLecture(@Valid @RequestBody LectureRequest lectureRequest) {

        lectureService.createLecture(lectureRequest);

    }

    @GetMapping("/{alias}")
    LectureDetailResponse getLectureByAlias(@PathVariable String alias) {

        return lectureService.getLectureByAlias(alias);

    }

    @GetMapping
    public Page<LectureDetailResponse> getAllLectures(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return lectureService.getAllLectures(page, size);
    }


    @PutMapping("/{alias}")
    public LectureResponse updateLecture(@PathVariable String alias,
                                         @Valid @RequestBody LectureUpdateRequest lectureUpdateRequest) {

        return lectureService.updateLectureByAlias(alias, lectureUpdateRequest);
    }


    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLecture(@PathVariable String alias) {

        lectureService.deleteLectureByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableLecture(@PathVariable String alias) {

        lectureService.enableLectureByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableLecture(@PathVariable String alias) {

        lectureService.disableLectureByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<LectureDetailResponse> filterLectures(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return lectureService.filterLectures(filterDto, page, size);
    }

}
