package co.istad.lms.features.course;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.course.dto.CourseDetailResponse;
import co.istad.lms.features.course.dto.CourseRequest;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.course.dto.CourseUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void creatCourse(@Valid @RequestBody CourseRequest courseRequest) {

        courseService.createCourse(courseRequest);

    }

    @GetMapping("/{alias}")
    CourseDetailResponse getCourseByAlias(@PathVariable String alias) {

        return courseService.getCourseByAlias(alias);

    }

    @GetMapping
    public Page<CourseDetailResponse> getAllDegrees(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return courseService.getAllCourses(page, size);
    }

    @PutMapping("/{alias}")
    public CourseResponse updateCourse(@PathVariable String alias,
                                       @Valid @RequestBody CourseUpdateRequest courseUpdateRequest) {

        return courseService.updateCourseByAlias(alias, courseUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    public void deleteCourse(@PathVariable String alias) {

        courseService.deleteCourseByAlias(alias);
    }

    @PatchMapping("/{alias}/enable")
    void enableCourse(@PathVariable String alias){

        courseService.enableCourseByAlias(alias);
    }

    @PatchMapping("/{alias}/disable")
    void disableCourse(@PathVariable String alias){

        courseService.disableCourseByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<CourseDetailResponse> filterCourses(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return courseService.filterCourses(filterDto, page, size);
    }
}
