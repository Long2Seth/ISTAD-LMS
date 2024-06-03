package co.istad.lms.features.course;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.course.dto.CourseDetailResponse;
import co.istad.lms.features.course.dto.CourseRequest;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.course.dto.CourseUpdateRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudySubjectRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('course:write')")
    void creatCourse(@Valid @RequestBody CourseRequest courseRequest) {

        courseService.createCourse(courseRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('course:read')")
    CourseDetailResponse getCourseByAlias(@PathVariable String alias) {

        return courseService.getCourseByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('course:read')")
    public Page<CourseDetailResponse> getAllCourses(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return courseService.getAllCourses(page, size);
    }

    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('course:update')")
    public CourseDetailResponse updateCourse(@PathVariable String alias,
                                             @Valid @RequestBody CourseUpdateRequest courseUpdateRequest) {

        return courseService.updateCourseByAlias(alias, courseUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('course:delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable String alias) {

        courseService.deleteCourseByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('course:delete')")
    void enableCourse(@PathVariable String alias) {

        courseService.enableCourseByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('course:delete')")
    void disableCourse(@PathVariable String alias) {

        courseService.disableCourseByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('course:read')")
    public Page<CourseDetailResponse> filterCourses(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return courseService.filterCourses(filterDto, page, size);
    }

    @PostMapping("/{alias}/instructors/{uuid}")
    @PreAuthorize("hasAnyAuthority('course:update')")
    public CourseDetailResponse addInstructorToCourse(
            @PathVariable String alias,
            @PathVariable String uuid) {

        return courseService.addInstructorToCourse(alias,uuid );

    }

    @DeleteMapping("/{alias}/instructor/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('course:delete')")
    public void deleteInstructorFromCourse(
            @PathVariable String alias,
            @PathVariable String uuid) {

        courseService.deleteInstructorFromCourse(alias,uuid);

    }
}
