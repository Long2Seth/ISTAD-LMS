package co.istad.lms.features.score;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.score.dto.ScoreDetailResponse;
import co.istad.lms.features.score.dto.ScoreRequest;
import co.istad.lms.features.score.dto.ScoreUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage score
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface ScoreService {

    /**
     * Creates a new score
     *
     * @param scoreRequest is the request object containing score details
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createScore(ScoreRequest scoreRequest);


    /**
     * Retrieves the details of a score by its uuid.
     *
     * @param uuid is the unique identify of score
     * @return {@link ScoreDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ScoreDetailResponse getScoreByUuid(String uuid);

    /**
     * Retrieves a paginated list of all scores
     *
     * @param pageNumber is the current pageNumber number to retrieve
     * @param pageSize is the pageSize of record per pageNumber to retrieve
     * @return {@link Page<ScoreDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ScoreDetailResponse> getAllScores(int pageNumber, int pageSize);

    /**
     * Updates an existing score by its alias.
     *
     * @param uuid               is the unique identify of score
     * @param scoreUpdateRequest is the request object containing the updated score details
     * @return {@link DegreeDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    ScoreDetailResponse updateScoreByUuid(String uuid, ScoreUpdateRequest scoreUpdateRequest);

    /**
     * Delete a score by its uuid
     *
     * @param uuid is the unique identify of score
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteScoreByUuid(String uuid);

    /**
     * Filters degree based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param pageNumber      is the current pageNumber number
     * @param pageSize      pageSize of record per pageNumber to retrieve
     * @return {@link Page<ScoreDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<ScoreDetailResponse> filterScores(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);

}
