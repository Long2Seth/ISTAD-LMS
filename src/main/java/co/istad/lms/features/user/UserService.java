package co.istad.lms.features.user;



import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;
import co.istad.lms.features.user.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {


    Page<UserResponse> getAllUsers(int page , int limit);

    Page<UserResponseDetail> getAllUsersDetail(int page , int limit);


    Page<UserResponse> getAllUsersWithAdminRole(int page, int limit);


    UserResponse getUserById(String uuid);

    UserResponseDetail getUserDetailById(String uuid);


    UserResponse createUser(UserRequest userRequest);


    UserResponse updateUser(String uuid, UserUpdateRequest userRequest);


    void deleteUser(String uuid);


    UserResponse disableUser(String uuid);


    UserResponse enableUser(String uuid);


    UserResponse isDeleted(String uuid);


}
