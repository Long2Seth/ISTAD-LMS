package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.*;
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
     * @param pageNumber is the pageNumber number to retrieve
     * @param pageSize is the pageSize of the pageNumber to retrieve
     * @return * @return {@link Page<AdmissionResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<AdmissionDetailResponse> getAllAdmissions(int pageNumber, int pageSize);

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
     *
     * @param uuid is the unique identify of admission
     * @param admissionUpdateStatusRequest is the status of admission(0=close, 1=open,2=archive)
     */
    void updateAdmissionStatus(String uuid, AdmissionUpdateStatusRequest admissionUpdateStatusRequest);

    /**
     * Filters admissions based on the provided criteria.
     *
     * @param filterDto is the object use for filter any column, any operation
     * @param pageNumber      is the pageNumber number of current to retrieve
     * @param pageSize      is the pageSize of record per pageNumber
     * @return {@link  Page<AdmissionDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<AdmissionDetailResponse> filterAdmissions(BaseSpecification.FilterDto filterDto, int pageNumber, int pageSize);

}

