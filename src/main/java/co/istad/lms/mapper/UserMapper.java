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





    User fromAdminRequest(AdminRequest adminRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromAdminRequest(@MappingTarget User user, AdminRequestUpdate adminRequestUpdate);






    User fromAcademicRequest(AcademicRequest academicRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromAcademicRequest(@MappingTarget User user, AcademicRequestUpdate academicRequestUpdate);







    User fromInstructorRequest(InstructorRequest instructorRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromInstructorRequest(@MappingTarget User user, InstructorRequestUpdate instructorRequestUpdate);






    User fromStaffRequest(StaffRequest staffRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromStaffRequest(@MappingTarget User user, StaffRequestUpdate staffRequestUpdate);






    User fromStudentRequest(StudentRequest studentRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromStudentRequest(@MappingTarget User user, StudentRequestUpdate studentRequestUpdate);




}
