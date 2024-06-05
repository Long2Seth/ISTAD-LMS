package co.istad.lms.mapper;


import co.istad.lms.domain.Admission;
import co.istad.lms.domain.User;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.features.password.dto.ResponsePassword;
import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;
import co.istad.lms.features.user.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    UserResponseDetail toUserResponseDetail(User user);

    User fromUserRequest(UserRequest userRequest);

    ResponsePassword toResponsePassword(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(@MappingTarget User user, UserUpdateRequest userRequest);


}
