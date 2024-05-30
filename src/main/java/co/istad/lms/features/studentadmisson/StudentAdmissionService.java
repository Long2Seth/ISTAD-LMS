package co.istad.lms.features.studentadmisson;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.AdmissionService;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionDetailResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionRequest;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage student admissions
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface StudentAdmissionService {

    /**
     * create new student admission
     *
     * @param studentAdmissionRequest object for create new student admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createStudentAdmission(StudentAdmissionRequest studentAdmissionRequest);

    /**
     * Retrieves the details of a student admission by its UUID.
     *
     * @param uuid is the unique identifier of student admission
     * @return {@link StudentAdmissionDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    StudentAdmissionDetailResponse getStudentAdmissionByUuid(String uuid);

    /**
     * Retrieves a paginated list of all student admissions.
     *
     * @param page is the page number to retrieve
     * @param size is the size of the page to retrieve
     * @return * @return {@link Page<StudentAdmissionResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<StudentAdmissionResponse> getAllStudentAdmissions(int page, int size);

    /**
     * Updates an existing student admission.
     *
     * @param uuid                          is the unique identifier of admission
     * @param studentAdmissionUpdateRequest the request object containing the updated student admission details
     * @return {@link StudentAdmissionDetailResponse}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    StudentAdmissionDetailResponse updateStudentAdmission(String uuid, StudentAdmissionUpdateRequest studentAdmissionUpdateRequest);

    /**
     * Delete  admission by  UUID.
     *
     * @param uuid is the unique identifier of student admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void deleteStudentAdmission(String uuid);

    /**
     * Disables student admission by  UUID.
     *
     * @param uuid is the unique identifier of student admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void disableStudentAdmissionByUuid(String uuid);

    /**
     * Enables a student admission by its UUID.
     *
     * @param uuid is the unique identifier of student admission
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void enableStudentAdmissionByUuid(String uuid);

    /**
     * Filters Student admissions based on the provided criteria.
     *
     * @param filterDto is the object use for filter any column, any operation
     * @param page      is the page number of current to retrieve
     * @param size      is the size of record per page
     * @return {@link  Page<StudentAdmissionDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<StudentAdmissionDetailResponse> filterStudentAdmissions(BaseSpecification.FilterDto filterDto, int page,
                                                                 int size);
}

