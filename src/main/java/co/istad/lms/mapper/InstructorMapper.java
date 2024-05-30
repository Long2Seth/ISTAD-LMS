package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    Instructor toRequest(InstructorRequest request);

    @Mapping(source = "user", target = "user")
    InstructorResponse toResponse(Instructor instructor);

    @Mapping(source = "user", target = "user")
    InstructorRequestDetail toResponseDetail(Instructor instructor);
}
