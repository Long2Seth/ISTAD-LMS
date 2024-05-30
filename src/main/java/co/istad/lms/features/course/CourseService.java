package co.istad.lms.features.course;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.course.dto.*;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage courses
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface CourseService {

    /**
     * Creates a new course.
     *
     * @param courseRequest is the request object containing course details
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createCourse(CourseRequest courseRequest);

    /**
     * Retrieves the details of a course by its alias.
     *
     * @param alias is the unique name of degree
     * @return {@link CourseDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    CourseDetailResponse getCourseByAlias(String alias);

    /**
     * Retrieves a paginated list of all courses.
     *
     * @param page is the current page number to retrieve
     * @param size is the size of record per page to retrieve
     * @return {@link Page <CourseDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<CourseDetailResponse> getAllCourses(int page, int size);

    /**
     * Updates an existing course by its alias.
     *
     * @param alias               is the unique name of course
     * @param courseUpdateRequest is the request object containing the updated course details
     * @return {@link CourseDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    CourseDetailResponse updateCourseByAlias(String alias, CourseUpdateRequest courseUpdateRequest);

    /**
     * Deletes a course by its alias.
     *
     * @param alias is the unique name of course
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteCourseByAlias(String alias);

    /**
     * Enable course by alias
     *
     * @param alias is the unique name of course
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableCourseByAlias(String alias);

    /**
     * Disable course by alias
     *
     * @param alias is the unique name of course
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableCourseByAlias(String alias);

    /**
     * Filters courses based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param page      is the current page number
     * @param size      size of record per page to retrieve
     * @return {@link Page<CourseDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<CourseDetailResponse> filterCourses(BaseSpecification.FilterDto filterDto, int page, int size);

    /**
     * add instructor to course
     *
     * @param alias is the unique name of course
     * @param uuid is the uuid of instructor to add to course
     * @return {@link CourseDetailResponse}
     */
    CourseDetailResponse addInstructorToCourse(String alias, String uuid);

    /**
     * @param alias is the unique name of course
     * @param uuid is the uuid of instructor to delete
     */
    void deleteInstructorFromCourse(String alias, String uuid);
}
