package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.instructor.dto.InstructorResponseDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface InstructorMapper {

    Instructor toRequest(InstructorRequest request);

    @Mapping(source = "user", target = "user")
    InstructorResponse toResponse(Instructor instructor);

    @Mapping(source = "user", target = "user")
    InstructorResponseDetail toResponseDetail(Instructor instructor);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "instructor.user", source = "instructorRequestDetail.user",qualifiedByName = "updateUserDetailFromRequest")
    void updateInstructorFromRequest(@MappingTarget Instructor instructor, InstructorRequestDetail instructorRequestDetail);


}
