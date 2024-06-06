package co.istad.lms.features.instructor;

import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.instructor.dto.InstructorResponseDetail;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage instructors
 * @since 1.0 (2024)
 * @author Long Piseth
 * @version 1.0

 */
public interface InstructorService {


    /**
     * Creates a new instructor.
     *
     * @param instructorRequest is the request object containing instructor details for create instructor
     * @return {@link InstructorResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    InstructorResponse createInstructor(InstructorRequest instructorRequest);



    /**
     * Updates an existing instructor.
     *
     * @param uuid    is the unique identifier of instructor
     * @param instructorRequestDetail the request object containing the updated instructor details
     * @return {@link InstructorResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    InstructorResponseDetail updateInstructorByUuid(String uuid, InstructorRequestDetail instructorRequestDetail);



    /**
     * Retrieves the details of an instructor by its UUID.
     *
     * @param uuid is the unique identifier of instructor
     * @return {@link InstructorResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    InstructorResponseDetail getInstructorDetailByUuid(String uuid);



    /**
     * Retrieves the details of an instructor by its UUID.
     *
     * @param uuid is the unique identifier of instructor
     * @return {@link InstructorResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    InstructorResponse getInstructorByUuid(String uuid);


    /**
     * Delete instructor by  UUID.
     *
     * @param uuid is the unique identifier of instructor
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deleteInstructorByUuid( String uuid);


    /**
     * Disables  instructor by  UUID.
     *
     * @param uuid is the unique identifier of instructor
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void disableInstructorByUuid( String uuid);



    /**
     * Enables an instructor by its UUID.
     *
     * @param uuid is the unique identifier of instructor
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void enableInstructorByUuid( String uuid);



    /**
     * Block an instructor by its UUID.
     *
     * @param uuid is the unique identifier of instructor
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void blockInstructorByUuid( String uuid);


    /**
     * Retrieves a paginated list details of all instructors.
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<InstructorResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<InstructorResponseDetail> getAllInstructorDetail(int page, int limit);



    /**
     * Retrieves a paginated list of all instructors.
     *
     * @param search is the search keyword to filter the list of instructors
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<InstructorResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<InstructorResponse> getAllInstructor(String search, int page, int limit);


}
