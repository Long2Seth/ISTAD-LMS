package co.istad.lms.features.user;

import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserResponse> getAllUsers(int page , int limit);
    UserResponse getUserById(Long id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);

}
