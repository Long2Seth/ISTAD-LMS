package co.istad.lms.features.staff;

import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage staffs
 * @since 1.0 (2024)
 * @author Long Piseth
 */
public interface StaffService {


    /**
     * Creates a new staff.
     *
     * @param staffRequest is the request object containing staff details for create staff
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void createStaff(StaffRequest staffRequest);



    /**
     * Updates an existing staff.
     *
     * @param uuid    is the unique identifier of staff
     * @param staffRequestDetail the request object containing the updated staff details
     * @return {@link StaffResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StaffResponseDetail updateStaffByUuid(String uuid, StaffRequestDetail staffRequestDetail);



    /**
     * Retrieves the details of a staff by its UUID.
     *
     * @param uuid is the unique identifier of staff
     * @return {@link StaffResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StaffResponseDetail getStaffDetailByUuid(String uuid);



    /**
     * Retrieves the details of a staff by its UUID.
     *
     * @param uuid is the unique identifier of staff
     * @return {@link StaffResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    StaffResponse getStaffByUuid(String uuid);



    /**
     * Deletes an existing staff.
     *
     * @param uuid    is the unique identifier of staff
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deleteStaffByUuid(String uuid);



    /**
     * Retrieves the details of all staffs.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<StaffResponseDetail>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<StaffResponseDetail> getStaffDetail(int page, int limit);


    /**
     * Retrieves the details of all staffs.
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<StaffResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<StaffResponse> getStaff(int page, int limit);


    /**
     * Disables a staff by its UUID.
     * @param uuid is the unique identifier of staff
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void disableByUuid(String uuid);



    /**
     * Enables a staff by its UUID.
     *
     * @param uuid is the unique identifier of staff
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void enableByUuid(String uuid);



    /**
     * Block a staff by its UUID.
     *
     * @param uuid is the unique identifier of staff
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void updateDeletedStatus(String uuid);
}
