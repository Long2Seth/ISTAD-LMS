package co.istad.lms.features.generation;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.generation.dto.GenerationDetailResponse;
import co.istad.lms.features.generation.dto.GenerationRequest;
import co.istad.lms.features.generation.dto.GenerationResponse;
import co.istad.lms.features.generation.dto.GenerationUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage generation
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface GenerationService {

    /**
     *Create new generation
     *
     * @param generationRequest is the object that contain information to create new faculty
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createGeneration(GenerationRequest generationRequest);

    /**
     * Retrieves faculty by alias
     *
     * @param alias is the alias of generation
     * @return {@link GenerationDetailResponse}
     */
    GenerationDetailResponse getGenerationByAlias(String alias);

    /**
     * Retrieves a paginated list of all generations.
     *
     * @param pageNumber is the pageNumber number to retrieve
     * @param pageSize is the pageSize of the pageNumber to retrieve
     * @return  * @return {@link Page<GenerationDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<GenerationDetailResponse> getAllGenerations(int pageNumber, int pageSize);

    /**
     * Update generation by alias
     *
     * @param alias                is the unique name
     * @param generationUpdateRequest is the object that contain entity information to update
     * @return {@link GenerationDetailResponse
     * @since 1.0 (2024)
     */
    GenerationDetailResponse updateGenerationByAlias(String alias, GenerationUpdateRequest generationUpdateRequest);

    /**
     * Delete generation by alias
     *
     * @param alias is the unique name
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteGenerationByAlias(String alias);

    /**
     * Disables generation by alias
     *
     * @param alias is the unique name of faculty
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableGenerationByAlias(String alias);

    /**
     * Enables generation by alias
     *
     * @param alias is the unique name
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableGenerationByAlias(String alias);

    /**
     * filter generation by dynamic field and operation
     *
     * @param filterDto is the object that use for filter
     * @param pageNumber      is the current pageNumber request
     * @param pageSize      is the pageSize of record per pageNumber
     * @return {@link Page<GenerationDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<GenerationDetailResponse> filterGenerations(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);

    /**
     * public generation by alias
     *
     * @param alias is the unique name
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void publicGenerationByAlias(String alias);

    /**
     * draft generation by alias
     *
     * @param alias is the unique name
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void draftGenerationByAlias(String alias);
}
