package co.istad.lms.features.degree;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage degrees
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface DegreeService {

    /**
     * Creates a new degree.
     *
     * @param degreeRequest is the request object containing degree details
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createDegree(DegreeRequest degreeRequest);

    /**
     * Retrieves the details of a degree by its alias.
     *
     * @param alias is the unique name of degree
     * @return {@link DegreeDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    DegreeDetailResponse getDegreeByAlias(String alias);

    /**
     * Retrieves a paginated list of all degrees.
     *
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the pageSize of record per pageNumber to retrieve
     * @return {@link Page<DegreeDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<DegreeDetailResponse> getAllDegrees(int pageNumber, int pageSize);

    /**
     * Update an existing degree by its alias.
     *
     * @param alias               is the unique name of degree
     * @param degreeUpdateRequest is the request object containing the updated degree details
     * @return {@link DegreeDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    DegreeDetailResponse updateDegreeByAlias(String alias, DegreeUpdateRequest degreeUpdateRequest);

    /**
     * Delete a degree by its alias.
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteDegreeByAlias(String alias);


    /**
     * Enable degree by alias
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableDegreeByAlias(String alias);

    /**
     * Disable degree by alias
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */

    /**
     * public degree by alias
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void publicDegreeByAlias(String alias);

    /**
     * draft degree by alias
     *
     * @param alias is the unique name of degree
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void draftDegreeByAlias(String alias);

    void disableDegreeByAlias(String alias);

    /**
     * Filters degree based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param pageNumber      is the current pageNumber number
     * @param pageSize      pageSize of record per pageNumber to retrieve
     * @return {@link Page<DegreeDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<DegreeDetailResponse> filterDegrees(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);
}
