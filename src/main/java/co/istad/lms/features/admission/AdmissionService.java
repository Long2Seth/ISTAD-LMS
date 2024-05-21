package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Admission;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.springframework.data.domain.Page;


public interface AdmissionService {

    /**
     * Creates a new admission.
     *
     * @param admissionCreateRequest the request object containing admission details for create admission
     * @return the response object containing the created admission details
     */
    void createAdmission(AdmissionCreateRequest admissionCreateRequest);

    /**
     * Retrieves the details of an admission by its UUID.
     *
     * @param uuid the UUID of the admission
     * @return the response object containing the admission details
     */
    AdmissionDetailResponse getAdmissionByUuid(String uuid);

    /**
     * Retrieves a paginated list of all admissions.
     *
     * @param page the page number to retrieve
     * @param size the size of the page to retrieve
     * @return a paginated list of admissions
     */
    Page<AdmissionResponse> getAllAdmissions(int page, int size);

    /**
     * Updates an existing admission.
     *
     * @param admissionUuid    the UUID of the admission to update
     * @param admissionRequest the request object containing the updated admission details
     * @return the response object containing the updated admission details
     */
    AdmissionResponse updateAdmission(String admissionUuid, AdmissionUpdateRequest admissionRequest);

    /**
     * Deletes an admission by its UUID.
     *
     * @param admissionUuid the UUID of the admission to delete
     */
    void deleteAdmission(String admissionUuid);

    /**
     * Disables an admission by its UUID.
     *
     * @param uuid the UUID of the admission to disable
     */
    void disableAdmission(String uuid);

    /**
     * Enables an admission by its UUID.
     *
     * @param uuid the UUID of the admission to enable
     */
    void enableAdmission(String uuid);

    /**
     * Filters admissions based on the provided criteria.
     *
     * @param filterDto use for filter any column, any operation
     * @param page     the page number to retrieve
     * @param size     the size of the page to retrieve
     * @return a paginated list of filtered admissions
     */
    Page<AdmissionDetailResponse> filterAdmissions(BaseSpecification.FilterDto filterDto, int page, int size);
}
