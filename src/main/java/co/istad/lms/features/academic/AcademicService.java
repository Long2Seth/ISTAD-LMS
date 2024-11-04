package co.istad.lms.features.academic;

import co.istad.lms.features.academic.dto.*;
import org.springframework.data.domain.Page;


/**
 * This interface is used to define the methods that are used to interact with the academic
 * repository and to perform the business logic related to academic
 *
 * @author Long Piseth
 * @since 1.0 (2024)
 *
 */
public interface AcademicService {


    /**
     * Create a new academic
     * @param academicRequest is the request object to create a new academic record
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void createAcademic(AcademicRequest academicRequest);


    /**
     * Update an existing academic
     * @param uuid is the unique identifier of academic
     * @param academicRequestUpdate is the request object containing the updated academic details
     * @return {@link AcademicResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    AcademicResponseDetail updateAcademicByUuid(String uuid , AcademicRequestUpdate academicRequestUpdate);



    /**
     * Retrieve the details of an academic by its UUID
     * @param uuid is the unique identifier of academic
     * @return {@link AcademicResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    AcademicResponseDetail getAcademicDetailByUuid(String uuid);



    /**
     * Display some fields of an academic by its UUID
     * @param uuid is the unique identifier of academic
     * @return {@link AcademicResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    AcademicResponse getAcademicsByUuid(String uuid);



    /**
     * Delete an academic by its UUID that is hard delete
     * @param uuid is the unique identifier of academic
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void deleteAcademicByUuid(String uuid);



    /**
     * Update the status of an academic to disabled by its UUID
     * @param uuid is the unique identifier of academic
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void updateDisableAcademicByUuid(String uuid);



    /**
     * Update the status of an academic to enabled by its UUID
     * @param uuid is the unique identifier of academic
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void updateEnableAcademicByUuid(String uuid);



    /**
     * Update the status of an academic to deleted by its UUID that is soft delete
     * @param uuid is the unique identifier of academic
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    void updateDeletedAcademicByUuid(String uuid);



    /**
     * Retrieve a paginated list of all academics
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<AcademicResponseDetail>}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    Page<AcademicResponseDetail> getAcademics(int page, int limit);




    /**
     * Retrieve a paginated list of all academics with some fields
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<AcademicResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     *
     */
    Page<AcademicResponse> getAcademicsDetail(int page, int limit);



}
