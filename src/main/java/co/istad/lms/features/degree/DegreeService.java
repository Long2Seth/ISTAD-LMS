package co.istad.lms.features.degree;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.springframework.data.domain.Page;

public interface DegreeService {

    /**
     * Creates a new degree.
     *
     * @param degreeRequest the request object containing degree details
     *                      finished
     */
    void createDegree(DegreeRequest degreeRequest);

    /**
     * Retrieves the details of a degree by its alias.
     *
     * @param alias the alias of the degree
     * @return the response object containing the degree details
     * finished
     */
    DegreeDetailResponse getDegreeByAlias(String alias);

    /**
     * Retrieves the details of a degree by its level.
     *
     * @param level the level of the degree
     * @return the response object containing the degree details
     * @apiNote finished
     */

    /**
     * Retrieves a paginated list of all degrees.
     *
     * @param page the page number to retrieve
     * @param size the size of the page to retrieve
     * @return a paginated list of degrees
     */
    Page<DegreeDetailResponse> getAllDegrees(int page, int size);

    /**
     * Retrieves a paginated list of all degrees.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize   the size of the page to retrieve
     * @return a paginated list of degrees
     */

    /**
     * Updates an existing degree by its alias.
     *
     * @param alias               the alias of the degree to update
     * @param degreeUpdateRequest the request object containing the updated degree details
     * @return the response object containing the updated degree details
     */
    DegreeResponse updateDegreeByAlias(String alias, DegreeUpdateRequest degreeUpdateRequest);

    /**
     * Updates an existing degree by its level.
     *
     * @param level               the level of the degree to update
     * @param degreeUpdateRequest the request object containing the updated degree details
     * @return the response object containing the updated degree details
     */

    /**
     * Deletes a degree by its alias.
     *
     * @param alias the alias of the degree to delete
     */
    void deleteDegreeByAlias(String alias);


    /**
     * @param filterDto use for filter by any field
     * @param page      the current page number
     * @param size      size of page
     * @return return Detail of degree
     */
    Page<DegreeDetailResponse> filterDegree(BaseSpecification.FilterDto filterDto, int page, int size);
}
