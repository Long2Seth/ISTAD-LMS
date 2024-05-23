package co.istad.lms.features.yearofstudy;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyRequest;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing YearOfStudy.
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface YearOfStudyService {

    /**
     * Creates a new YearOfStudy.
     *
     * @param yearOfStudyRequest is the request object containing the details of the yearOfStudy to be created
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createYearOfStudy(YearOfStudyRequest yearOfStudyRequest);

    /**
     * Retrieves the details of a yearOfStudy
     *
     * @param uuid is the unique name of yearOfStudy
     * @return {@link YearOfStudyDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    YearOfStudyDetailResponse getYearOfStudyByUuid(String uuid);


    /**
     * Retrieves a paginated list of all yearOfStudy.
     *
     * @param page is the current page number to retrieve
     * @param size is the number record per page to retrieve
     * @return {@link Page<YearOfStudyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<YearOfStudyDetailResponse> getAllYearOfStudies(int page, int size);

    /**
     * Updates an existing yearOfStudy by its uuid.
     *
     * @param uuid is the unique name of yearOfStudy
     * @param yearOfStudyUpdateRequest the request object containing the updated details of the yearOfStudy
     * @return {@link YearOfStudyResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    YearOfStudyResponse updateYearOfStudyByUuid(String uuid, YearOfStudyUpdateRequest yearOfStudyUpdateRequest);


    /**
     * Deletes a YearOfStudy by its uuid.
     *
     * @param uuid is the unique name of yearOfStudy
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteYearOfStudyByUuid(String uuid);

    /**
     * filter yearOfStudy by any field
     *
     * @param filterDto is object for request to filter
     * @param page is the current page number to retrieve
     * @param size is the number record per page to retrieve
     * @return {@link Page<YearOfStudyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<YearOfStudyDetailResponse> filterYearOfStudy(BaseSpecification.FilterDto filterDto, int page, int size);
}
