package co.istad.lms.features.material;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.material.dto.MaterialDetailResponse;
import co.istad.lms.features.material.dto.MaterialRequest;
import co.istad.lms.features.material.dto.MaterialResponse;
import co.istad.lms.features.material.dto.MaterialUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage materials
 *
 * @author Nouth Chanreaksa
 * @since 1.0 (2024)
 */
public interface MaterialService {

    /**
     * Creates a new me.
     *
     * @param materialRequest is the request object containing material details
     * @author Nouth Chanreaksa
     * @since 1.0 (2024)
     */

    void createMaterial(MaterialRequest materialRequest);

    /**
     * Retrieves the details of a material by its alias.
     *
     * @param alias is the unique name of material
     * @return {@link MaterialDetailResponse}
     * @author Nouth Chanraeksa
     * @since 1.0 (2024)
     */
    MaterialDetailResponse getMaterialByAlias(String alias);


    /**
     * Updates an existing material by its alias.
     *
     * @param alias                 is the unique name of material
     * @param materialUpdateRequest is the request object containing the updated subject details
     * @return {@link MaterialDetailResponse}
     * @author Nouth Chanreaksa
     * @since 1.0 (2024)
     */
    MaterialDetailResponse updateMaterialByAlias(String alias, MaterialUpdateRequest materialUpdateRequest);

    Page<MaterialDetailResponse> getAllMaterials(int pageNumber, int pageSize);


    /**
     * Deletes a material by its alias.
     *
     * @param alias is the unique name of subject
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void deleteMaterialByAlias(String alias);


    /**
     * Enable material by alias
     *
     * @param alias is the unique name of material
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void enableMaterialByAlias(String alias);

    /**
     * Disable material by alias
     *
     * @param alias is the unique name of material
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void disableMaterialByAlias(String alias);

    /**
     * Filters material based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param pageNumber      is the current pageNumber number
     * @param pageSize      pageSize of record per pageNumber to retrieve
     * @return {@link Page<MaterialDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<MaterialDetailResponse> filterMaterials(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);
}

