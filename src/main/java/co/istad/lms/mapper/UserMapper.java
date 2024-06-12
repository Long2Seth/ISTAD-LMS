package co.istad.lms.mapper;


import co.istad.lms.domain.Admission;
import co.istad.lms.domain.User;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestUpdate;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestUpdate;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestUpdate;
import co.istad.lms.features.password.dto.ResponsePassword;
import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestUpdate;
import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestUpdate;
import co.istad.lms.features.user.dto.*;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    UserResponseDetail toUserResponseDetail(User user);

    User fromUserRequest(UserRequest userRequest);

    ResponsePassword toResponsePassword(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(@MappingTarget User user, UserUpdateRequest userRequest);

    @Named("updateUserDetailFromRequest")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDetailFromRequest(@MappingTarget User user, UserRequestDetail userRequest);


    @Mapping(target = "uuid", ignore = true) // UUID will be set in the service layer
    @Mapping(target = "password", ignore = true) // Password will be encoded in the service layer
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "isChangePassword", constant = "false")
    User fromAdminRequest(AdminRequest adminRequest);

    @Mapping(target = "uuid", ignore = true) // UUID will be set in the service layer
    @Mapping(target = "password", ignore = true) // Password will be encoded in the service layer
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "isChangePassword", constant = "false")
    User fromAcademicRequest(AcademicRequest academicRequest);

    @Mapping(target = "uuid", ignore = true) // UUID will be set in the service layer
    @Mapping(target = "password", ignore = true) // Password will be encoded in the service layer
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "isChangePassword", constant = "false")
    User fromInstructorRequest(InstructorRequest instructorRequest);

    @Mapping(target = "uuid", ignore = true) // UUID will be set in the service layer
    @Mapping(target = "password", ignore = true) // Password will be encoded in the service layer
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "isChangePassword", constant = "false")
    User fromStaffRequest(StaffRequest staffRequest);



    @Mapping(target = "uuid", ignore = true) // UUID will be set in the service layer
    @Mapping(target = "password", ignore = true) // Password will be encoded in the service layer
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "isChangePassword", constant = "false")
    User fromStudentRequest(StudentRequest studentRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromAdminRequest(@MappingTarget User user, AdminRequestUpdate adminRequestUpdate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromAcademicRequest(@MappingTarget User user, AcademicRequestUpdate academicRequestUpdate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromInstructorRequest(@MappingTarget User user, InstructorRequestUpdate instructorRequestUpdate);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromStaffRequest(@MappingTarget User user, StaffRequestUpdate staffRequestUpdate);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromStudentRequest(@MappingTarget User user, StudentRequestUpdate studentRequestUpdate);


}
