package co.istad.lms.features.attendance;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.attendance.dto.AttendanceDetailResponse;
import co.istad.lms.features.attendance.dto.AttendanceRequest;
import co.istad.lms.features.attendance.dto.AttendanceResponse;
import co.istad.lms.features.attendance.dto.AttendanceUpdateRequest;
import org.springframework.data.domain.Page;

/**
 * Business logic interface which contains to manage degrees
 *
 * @author Nouth Chanraksa
 * @since 1.0 (2024)
 */
public interface AttendanceService {

    /**
     * Creates a new attendance.
     *
     * @param attendanceRequest is the request object containing attendance details
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void createAttendance(AttendanceRequest attendanceRequest);

    /**
     * Retrieves the details of an attendance by its uuid.
     *
     * @param uuid is the unique name of attendance
     * @return {@link AttendanceDetailResponse}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    AttendanceDetailResponse getAttendanceByUuid(String uuid);

    /**
     * Retrieves a paginated list of all attendance.
     *
     * @param page is the current page number to retrieve
     * @param size is the size of record per page to retrieve
     * @return {@link Page<AttendanceDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<AttendanceDetailResponse> getAllAttendances(int page, int size);

    /**
     * Updates an existing degree by its uuid.
     *
     * @param uuid               is the unique name of attendance
     * @param attendanceUpdateRequest is the request object containing the updated degree details
     * @return {@link AttendanceResponse}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    AttendanceResponse updateAttendanceByUuid(String uuid, AttendanceUpdateRequest attendanceUpdateRequest);

    /**
     * Deletes an attendance by its uuid.
     *
     * @param uuid is the unique name of attendance
     * @author Nouth chanraksa
     * @since 1.0 (2024)
     */
    void deleteAttendanceByUuid(String uuid);


    /**
     * Enable attendance by uuid
     *
     * @param uuid is the unique name of attendance
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void enableAttendanceByUuid(String uuid);

    /**
     * Disable attendance by uuid
     *
     * @param uuid is the unique name of attendance
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    void disableAttendanceByUuid(String uuid);

    /**
     * Filters attendance based on the specified criteria and retrieves a paginated list of results.
     *
     * @param filterDto is the request object use for filter by any column
     * @param page      is the current page number
     * @param size      size of record per page to retrieve
     * @return {@link Page<AttendanceDetailResponse>}
     * @author Nouth Chanraksa
     * @since 1.0 (2024)
     */
    Page<AttendanceDetailResponse> filterAttendance(BaseSpecification.FilterDto filterDto, int page, int size);
}
