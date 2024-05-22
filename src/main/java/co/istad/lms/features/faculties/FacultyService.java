package co.istad.lms.features.faculties;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage faculties
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface FacultyService {


    /**
     *Create new faculty
     *
     * @param facultyRequest is the object that contain information to create new faculty
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createFaculty(FacultyRequest facultyRequest);


    /**
     * Retrieves faculty by alias
     *
     * @param alias is the alias of faculty
     * @return {@link FacultyDetailResponse}
     */
    FacultyDetailResponse getFacultyByAlias(String alias);

    /**
     * Retrieves a paginated list of all admissions.
     *
     * @param page is the page number to retrieve
     * @param size is the size of the page to retrieve
     * @return  * @return {@link Page< FacultyDetailResponse >}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */

    Page<FacultyDetailResponse> getAllFaculties(int page, int size);

    /**
     * Update faculty by alias
     *
     * @param alias is the unique name
     * @param facultyUpdateRequest is the object that contain entity information to update
     * @return {@link FacultyResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */

    FacultyResponse updateFacultyByAlias(String alias, FacultyUpdateRequest facultyUpdateRequest);

    /**
     * Delete faculty by alias
     *
     * @param alias is the unique name
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteFacultyByAlias(String alias);


    /**
     * Disables faculty by alias
     *
     * @param alias is the unique name of faculty
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableFacultyByAlias(String alias);

    /**
     * Enables faculty by alias
     *
     * @param alias is the unique name
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableFacultyByAlias(String alias);


    /**
     *
     * @param filterDto is the object that use for filter
     * @param page is the current page request
     * @param size is the size of record per page
     * @return {@link Page<FacultyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<FacultyDetailResponse> filterFaculties(BaseSpecification.FilterDto filterDto, int page, int size);
}
