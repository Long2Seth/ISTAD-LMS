package co.istad.lms.features.graduation;

import co.istad.lms.features.graduation.dto.GraduationRequest;
import co.istad.lms.features.graduation.dto.GraduationResponse;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage graduations
 * @author Long Piseth
 * @since 1.0 (2024)
 */
public interface GraduationService {


    /**
     * Retrieves a paginated list of all graduations.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<GraduationResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<GraduationResponse> findAll(int page, int limit);



    /**
     * Retrieves the details of a graduation by its UUID.
     *
     * @param uuid is the unique identifier of graduation
     * @return {@link GraduationResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    GraduationResponse findById(String uuid);



    /**
     * Creates a new graduation.
     *
     * @param graduationRequest is the request object containing graduation details for create graduation
     * @return {@link GraduationResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    GraduationResponse createGraduation (GraduationRequest graduationRequest);



    /**
     * Updates an existing graduation.
     *
     * @param uuid    is the unique identifier of graduation
     * @param graduationRequest the request object containing the updated graduation details
     * @return {@link GraduationResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    GraduationResponse updateGraduation (String uuid, GraduationRequest graduationRequest);



    /**
     * Delete graduation by  UUID that hard delete.
     *
     * @param uuid is the unique identifier of graduation
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deleteGraduation (String uuid);


}
