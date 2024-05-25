package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.features.intructor.dto.InstructorRequest;
import co.istad.lms.features.intructor.dto.InstructorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    Instructor toRequest(InstructorRequest request);

    @Mapping(source = "user", target = "users")
    InstructorResponse toResponse(Instructor instructor);
}
