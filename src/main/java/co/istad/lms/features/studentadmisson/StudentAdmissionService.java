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
     * @param pageNumber is the pageNumber number to retrieve
     * @param pageSize is the pageSize of the pageNumber to retrieve
     * @return * @return {@link Page<StudentAdmissionResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<StudentAdmissionDetailResponse> getAllStudentAdmissions(int pageNumber, int pageSize);

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
     * @param pageNumber      is the pageNumber number of current to retrieve
     * @param pageSize      is the pageSize of record per pageNumber
     * @return {@link  Page<StudentAdmissionDetailResponse>}
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    Page<StudentAdmissionDetailResponse> filterStudentAdmissions(BaseSpecification.FilterDto filterDto, int pageNumber,
                                                                 int pageSize);
}

