package co.istad.lms.features.user;



import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<UserResponse> getAllUsers(int page , int limit);


    UserResponse getUserById(String alias);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(String alias, UserRequest userRequest);

    UserResponse deleteUser(String alias);

    UserResponse disableUser(String alias);

    UserResponse enableUser(String alias);

    UserResponse isDeleted(String alias);


}
