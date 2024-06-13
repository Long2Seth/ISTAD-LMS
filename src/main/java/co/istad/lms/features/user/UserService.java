package co.istad.lms.features.user;



import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;
import co.istad.lms.features.user.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Business logic interface which contains to manage users
 * @since 1.0 (2024)
 * @author Long Piseth
 */
public interface UserService {


    String generateStrongPassword(int length);

    /**
     * Retrieves a paginated list of all users.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<UserResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<UserResponse> getAllUsers(int page , int limit);


    /**
     * Retrieves a paginated list of all users.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<UserResponseDetail>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<UserResponseDetail> getAllUsersDetail(int page , int limit);


    /**
     * Retrieves a paginated list of all users with admin role.
     *
     * @param page is the pageNumber number to retrieve
     * @param limit is the pageSize of the pageNumber to retrieve
     * @return {@link Page<UserResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    Page<UserResponse> getAllUsersWithAdminRole(int page, int limit);



    /**
     * Retrieves a paginated list of all users with student role.
     *
     * @param uuid is the unique identifier of user
     * @return {@link Page<UserResponse>}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    UserResponse getUserById(String uuid);



    /**
     * Retrieves the details of a user by its UUID.
     *
     * @param uuid is the unique identifier of user
     * @return {@link UserResponseDetail}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    UserResponseDetail getUserDetailById(String uuid);



    /**
     * Creates a new user.
     *
     * @param userRequest is the request object containing user details for create user
     * @return {@link UserResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    UserResponse createUser(UserRequest userRequest);



    /**
     * Updates an existing user.
     *
     * @param uuid    is the unique identifier of user
     * @param userRequest the request object containing the updated user details
     * @return {@link UserResponse}
     * @author Long Piseth
     * @since 1.0 (2024)
     */
    UserResponse updateUser(String uuid, UserUpdateRequest userRequest);



    /**
     * Delete user by  UUID that hard delete.
     *
     * @param uuid is the unique identifier of user
     * @since 1.0 (2024)
     * @author Long Piseth
     */
    void deleteUser(String uuid);



    /**
     * Retrieves a paginated list of all users with student role.
     *
     * @param uuid is the unique identifier of user
     * @since 1.0 (2024)
     * @author Long Piseth
     */
    void disableUser(String uuid);


    /**
     * Retrieves a paginated list of all users with student role.
     *
     * @param uuid is the unique identifier of user
     * @since 1.0 (2024)
     * @author Long Piseth
     */
    void enableUser(String uuid);


    /**
     * Retrieves a paginated list of all users with student role.
     *
     * @param uuid is the unique identifier of user
     * @since 1.0 (2024)
     * @author Long Piseth
     */
    void isDeleted(String uuid);


}
