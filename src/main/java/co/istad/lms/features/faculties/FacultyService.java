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
     * Create new faculty
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
     * Retrieves a paginated list of all faculties.
     *
     * @param pageNumber is the pageNumber number to retrieve
     * @param pageSize is the pageSize of the pageNumber to retrieve
     * @return * @return {@link Page<FacultyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */

    Page<FacultyDetailResponse> getAllFaculties(int pageNumber, int pageSize);

    /**
     * Update faculty by alias
     *
     * @param alias                is the unique name
     * @param facultyUpdateRequest is the object that contain entity information to update
     * @return {@link FacultyDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */

    FacultyDetailResponse updateFacultyByAlias(String alias, FacultyUpdateRequest facultyUpdateRequest);

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
     * public faculty by alias
     *
     * @param alias is the unique name of faculty
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void publicFacultyByAlias(String alias);

    /**
     * private faculty by alias
     *
     * @param alias is the unique name of faculty
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void privateFacultyByAlias(String alias);


    /**
     * filter faculty by dynamic field and operation
     *
     * @param filterDto is the object that use for filter
     * @param pageNumber      is the current pageNumber request
     * @param pageSize      is the pageSize of record per pageNumber
     * @return {@link Page<FacultyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<FacultyDetailResponse> filterFaculties(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);
}
