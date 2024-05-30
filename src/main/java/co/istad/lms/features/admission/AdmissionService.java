package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage admissions
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface AdmissionService {

    /**
     * Creates a new admission.
     *
     * @param admissionRequest is the request object containing admission details for create admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createAdmission(AdmissionRequest admissionRequest);

    /**
     * Retrieves the details of an admission by its UUID.
     *
     * @param uuid is the unique identifier of admission
     * @return {@link AdmissionDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    AdmissionDetailResponse getAdmissionByUuid(String uuid);

    /**
     * Retrieves a paginated list of all student admissions.
     *
     * @param page is the page number to retrieve
     * @param size is the size of the page to retrieve
     * @return * @return {@link Page<AdmissionResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<AdmissionResponse> getAllAdmissions(int page, int size);

    /**
     * Updates an existing admission.
     *
     * @param uuid    is the unique identifier of admission
     * @param admissionRequest the request object containing the updated admission details
     * @return {@link AdmissionDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    AdmissionDetailResponse updateAdmission(String uuid, AdmissionUpdateRequest admissionRequest);

    /**
     * Delete admission by  UUID.
     *
     * @param admissionUuid is the unique identifier of admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteAdmission(String admissionUuid);

    /**
     * Disables  admission by  UUID.
     *
     * @param uuid is the unique identifier of admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableAdmissionByUuid(String uuid);

    /**
     * Enables an admission by its UUID.
     *
     * @param uuid is the unique identifier of admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableAdmissionByUuid(String uuid);

    /**
     * Filters admissions based on the provided criteria.
     *
     * @param filterDto is the object use for filter any column, any operation
     * @param page      is the page number of current to retrieve
     * @param size      is the size of record per page
     * @return {@link  Page<AdmissionDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<AdmissionDetailResponse> filterAdmissions(BaseSpecification.FilterDto filterDto, int page, int size);
}
