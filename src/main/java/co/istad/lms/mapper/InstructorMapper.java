package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.instructor.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface InstructorMapper {

    @Mapping(target = "user.dob" , ignore = true)
    Instructor toRequest(InstructorRequest request);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    InstructorResponse toResponse(Instructor instructor);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    InstructorResponseDetail toResponseDetail(Instructor instructor);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInstructorFromRequest(@MappingTarget Instructor instructor, InstructorRequestUpdate instructorRequestUpdate);


}
