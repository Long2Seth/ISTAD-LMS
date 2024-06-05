package co.istad.lms.features.yearofstudy;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.yearofstudy.dto.*;
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
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the number record per pageNumber to retrieve
     * @return {@link Page<YearOfStudyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<YearOfStudyDetailResponse> getAllYearOfStudies(int pageNumber, int pageSize);

    /**
     * Updates an existing yearOfStudy by its uuid.
     *
     * @param uuid is the unique name of yearOfStudy
     * @param yearOfStudyUpdateRequest the request object containing the updated details of the yearOfStudy
     * @return {@link YearOfStudyDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    YearOfStudyDetailResponse updateYearOfStudyByUuid(String uuid, YearOfStudyUpdateRequest yearOfStudyUpdateRequest);


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
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the number record per pageNumber to retrieve
     * @return {@link Page<YearOfStudyDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<YearOfStudyDetailResponse> filterYearOfStudy(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);


    /**
     * add subject to year of study
     *
     * @param uuid is the unique identity of yearOfStudy
     * @param yearOfStudySubjectRequest is the Set of subject alias to add
     * @return {@link YearOfStudyDetailResponse}
     */
    YearOfStudyDetailResponse addSubject(String uuid, YearOfStudySubjectRequest yearOfStudySubjectRequest);

    /**
     * delete a subject from yearOfStudy
     *
     * @param uuid is the uniq identity of yearOfStudy
     * @param alias is the alias of subject to delete
     */
    void deleteSubject(String uuid, String alias);
}
