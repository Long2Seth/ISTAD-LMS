package co.istad.lms.mapper;

import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentResponseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toRequest(StudentRequest studentRequest);

    @Mapping(source = "user", target = "user")
    StudentResponse toResponse(Student student);

    @Mapping(source = "user", target = "user")
    StudentResponseDetail toResponseDetail(Student student);

}
