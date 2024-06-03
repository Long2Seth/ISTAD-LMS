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
     * @param page is the current page number to retrieve
     * @param size is the size of record per page to retrieve
     * @return {@link Page<DegreeDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<DegreeDetailResponse> getAllDegrees(int page, int size);

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
    void disableDegreeByAlias(String alias);

    /**
     * Filters degree based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param page      is the current page number
     * @param size      size of record per page to retrieve
     * @return {@link Page<DegreeDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<DegreeDetailResponse> filterDegrees(BaseSpecification.FilterDto filterDto, int page, int size);
}
