package co.istad.lms.mapper;

import co.istad.lms.domain.Authority;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.User;
import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestUpdate;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestUpdate;
import co.istad.lms.features.course.dto.CourseWithUsersResponse;
import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestUpdate;
import co.istad.lms.features.instructor.dto.InstructorSettingRequest;
import co.istad.lms.features.password.dto.ResponsePassword;
import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestUpdate;
import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestUpdate;
import co.istad.lms.features.student.dto.StudentSettingRequest;
import co.istad.lms.features.user.dto.*;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "dob", ignore = true)
    User fromUserRequest(UserRequest userRequest);


    AuthorityResponse toAuthorityResponseFromUser(User user);


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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromStudentSettingRequest(@MappingTarget User user, StudentSettingRequest studentSettingRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromInstructorSettingRequest(@MappingTarget User user, InstructorSettingRequest instructorSettingRequest);

    @Named("toUserResponse")
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "nameEn", target = "nameEn")
    @Mapping(source = "nameKh", target = "nameKh")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "profileImage", target = "profileImage")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "user", target = "linkGit", qualifiedByName = "getLinkGit")
    @Mapping(source = "user", target = "linkTelegram", qualifiedByName = "getLinkTelegram")
    @Mapping(source = "user", target = "linkLinkedIn", qualifiedByName = "getLinkLinkedIn")
    @Mapping(source = "user", target = "skills", qualifiedByName = "getSkills")
    @Mapping(source = "user", target = "educations", qualifiedByName = "getEducations")
    UserResponse toUserResponse(User user);

    @Named("getLinkGit")
    default String getLinkGit(User user) {
        if (user.getAdmin() != null && user.getAdmin().getLinkGit() != null) {
            return user.getAdmin().getLinkGit();
        } else if (user.getStaff() != null && user.getStaff().getLinkGit() != null) {
            return user.getStaff().getLinkGit();
        } else if (user.getInstructor() != null && user.getInstructor().getLinkGit() != null) {
            return user.getInstructor().getLinkGit();
        } else if (user.getAcademic() != null && user.getAcademic().getLinkGit() != null) {
            return user.getAcademic().getLinkGit();
        }
        return null;
    }

    @Named("getLinkTelegram")
    default String getLinkTelegram(User user) {
        if (user.getAdmin() != null && user.getAdmin().getLinkTelegram() != null) {
            return user.getAdmin().getLinkTelegram();
        } else if (user.getStaff() != null && user.getStaff().getLinkTelegram() != null) {
            return user.getStaff().getLinkTelegram();
        } else if (user.getInstructor() != null && user.getInstructor().getLinkTelegram() != null) {
            return user.getInstructor().getLinkTelegram();
        } else if (user.getAcademic() != null && user.getAcademic().getLinkTelegram() != null) {
            return user.getAcademic().getLinkTelegram();
        }
        return null;
    }

    @Named("getLinkLinkedIn")
    default String getLinkLinkedIn(User user) {
        if (user.getAdmin() != null && user.getAdmin().getLinkLinkedin() != null) {
            return user.getAdmin().getLinkLinkedin();
        } else if (user.getStaff() != null && user.getStaff().getLinkLinkedin() != null) {
            return user.getStaff().getLinkLinkedin();
        } else if (user.getInstructor() != null && user.getInstructor().getLinkLinkedin() != null) {
            return user.getInstructor().getLinkLinkedin();
        } else if (user.getAcademic() != null && user.getAcademic().getLinkLinkedin() != null) {
            return user.getAcademic().getLinkLinkedin();
        }
        return null;
    }

    @Named("getSkills")
    default Set<String> getSkills(User user) {
        if (user.getAdmin() != null && user.getAdmin().getSkills() != null) {
            return user.getAdmin().getSkills();
        } else if (user.getStaff() != null && user.getStaff().getSkills() != null) {
            return user.getStaff().getSkills();
        } else if (user.getInstructor() != null && user.getInstructor().getSkills() != null) {
            return user.getInstructor().getSkills();
        } else if (user.getAcademic() != null && user.getAcademic().getSkills() != null) {
            return user.getAcademic().getSkills();
        }
        return null;
    }

    @Named("getEducations")
    default Set<String> getEducations(User user) {
        if (user.getAdmin() != null && user.getAdmin().getEducations() != null) {
            return user.getAdmin().getEducations();
        } else if (user.getStaff() != null && user.getStaff().getEducations() != null) {
            return user.getStaff().getEducations();
        } else if (user.getInstructor() != null && user.getInstructor().getEducations() != null) {
            return user.getInstructor().getEducations();
        } else if (user.getAcademic() != null && user.getAcademic().getEducations() != null) {
            return user.getAcademic().getEducations();
        }
        return null;
    }

    @Named("toUserResponseDetail")
    @Mappings({
            @Mapping(source = "uuid", target = "uuid"),
            @Mapping(source = "nameEn", target = "nameEn"),
            @Mapping(source = "nameKh", target = "nameKh"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "dob", target = "dob"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "profileImage", target = "profileImage"),
            @Mapping(source = "phoneNumber", target = "phoneNumber"),
            @Mapping(source = "currentAddress", target = "currentAddress"),
            @Mapping(source = "birthPlace", target = "birthPlace"),
            @Mapping(source = "position", target = "position")
    })
    UserResponseDetail toUserResponseDetail(User user);






}
