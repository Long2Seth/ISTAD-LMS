package co.istad.lms.mapper;


import co.istad.lms.domain.User;
import co.istad.lms.features.password.dto.ResponsePassword;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User fromUserRequest(UserRequest userRequest);

    ResponsePassword toResponsePassword(User user);



}
