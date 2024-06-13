package co.istad.lms.features.admin;

import co.istad.lms.features.admin.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;


/**
 * Business logic interface which contains to manage admins
 *
 * @author Long Piseth
 * @since 1.0 (2024)
 */
public interface AdminService {



    /**
     * Creates a new admin.
     *
     * @param adminRequest is the request object containing admin details for create admin
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void createAdmin(@Valid AdminRequest adminRequest);


    /**
     * Updates fields of an admin that are passed in the adminRequestDetail.
     *
     * @param uuid is the unique identifier of admin
     * @param adminRequestUpdate the request object containing the updated admin details
     * @return {@link AdminResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    AdminResponseDetail updateAdminByUuid (String uuid, AdminRequestUpdate adminRequestUpdate);


    /**
     * Retrieves a paginated list of all admins.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<AdminResponseDetail>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<AdminResponseDetail> getAdminsDetail(int page, int limit);



    /**
     * Retrieves a paginated list of all admins.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<AdminResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<AdminResponse> getAdmins(int page, int limit);


    /**
     * Retrieves the details of an admin by its UUID.
     *
     * @param uuid is the unique identifier of admin
     * @author Long Piseth
     * @return{@link AdminResponseDetail}
     * @since 1.0 (2024)
     */
    AdminResponseDetail getAdminDetailByUuid(String uuid);



    /**
     * Retrieves the details of an admin by its UUID.
     *
     * @param uuid is the unique identifier of admin
     * @return {@link AdminResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    AdminResponse  getAdminByUuid(String uuid);



    /**
     * Delete admin by  UUID.
     *
     * @param uuid is the unique identifier of admin
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void deleteAdminByUuid(String uuid);


    /**
     * Disables  admin by  UUID.
     *
     * @param uuid is the unique identifier of admin
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void disableAdminByUuid(String uuid);



    /**
     * Enables an admin by its UUID.
     *
     * @param uuid is the unique identifier of admin
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void enableAdminByUuid(String uuid);



    /**
     * Update status of an admin by UUID that
     *
     * @param uuid is the unique identifier of admin
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    void blockAdminByUuid(String uuid);


}
