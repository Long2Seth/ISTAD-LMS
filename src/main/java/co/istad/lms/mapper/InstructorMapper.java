package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.instructor.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface InstructorMapper {

    @Mapping(target = "user.dob" , ignore = true)
    Instructor toRequest(InstructorRequest request);

    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    InstructorResponse toResponse(Instructor instructor);

    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.currentAddress", target = "currentAddress")
    @Mapping(source = "user.birthPlace", target = "birthPlace")
    InstructorResponseDetail toResponseDetail(Instructor instructor);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInstructorFromRequest(@MappingTarget Instructor instructor, InstructorRequestUpdate instructorRequestUpdate);


}
