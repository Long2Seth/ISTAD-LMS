package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.student.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StudentMapper {

    @Mapping(target = "user.dob" , ignore = true)
    Student toRequest(StudentRequest studentRequest);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    StudentResponse toResponse(Student student);

//    @Mapping(source = "")
    StudentAchievementResponse toStudentAchievementResponse(Student student);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    StudentResponseDetail toResponseDetail(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromRequest(@MappingTarget Student student, StudentRequestUpdate studentRequestUpdate);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentSettingRequest(@MappingTarget Student student, StudentSettingRequest studentSettingRequest);


}
