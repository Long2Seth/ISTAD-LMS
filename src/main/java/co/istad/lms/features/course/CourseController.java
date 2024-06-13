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

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('course:read')")
    CourseDetailResponse getCourseByAlias(@PathVariable String uuid) {

        return courseService.getCourseByUuid(uuid);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('course:read')")
    public Page<CourseDetailResponse> getAllCourses(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return courseService.getAllCourses(pageNumber, pageSize);
    }

    @PatchMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('course:update')")
    public CourseDetailResponse updateCourse(@PathVariable String uuid,
                                             @Valid @RequestBody CourseUpdateRequest courseUpdateRequest) {

        return courseService.updateCourseByUuid(uuid, courseUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('course:delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable String uuid) {

        courseService.deleteCourseByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('course:delete')")
    void enableCourse(@PathVariable String uuid) {

        courseService.enableCourseByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('course:delete')")
    void disableCourse(@PathVariable String uuid) {

        courseService.disableCourseByUuid(uuid);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('course:read')")
    public Page<CourseDetailResponse> filterCourses(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return courseService.filterCourses(filterDto, pageNumber, pageSize);
    }

    @PostMapping("/{uuid}/instructors/{iuuid}")
    @PreAuthorize("hasAnyAuthority('course:update')")
    public CourseDetailResponse addInstructorToCourse(
            @PathVariable String uuid,
            @PathVariable String iuuid) {

        return courseService.addInstructorToCourse(uuid,iuuid );

    }

    @DeleteMapping("/{uuid}/instructor/{iuuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('course:delete')")
    public void deleteInstructorFromCourse(
            @PathVariable String uuid,
            @PathVariable String iuuid) {

        courseService.deleteInstructorFromCourse(uuid,iuuid);

    }
}
