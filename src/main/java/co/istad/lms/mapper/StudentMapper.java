package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.student.dto.*;
import co.istad.lms.features.user.dto.UserProfile;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StudentMapper {

    @Mapping(target = "user.dob" , ignore = true)
    Student toRequest(StudentRequest studentRequest);


    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    StudentResponse toResponse(Student student);

StudentCourseResponse toResponseCourse(Student student);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    StudentResponseDetail toResponseDetail(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromRequest(@MappingTarget Student student, StudentRequestUpdate studentRequestUpdate);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentSettingRequest(@MappingTarget Student student, StudentSettingRequest studentSettingRequest);


}
