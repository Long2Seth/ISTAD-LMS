package co.istad.lms.mapper;

import co.istad.lms.domain.Payment;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestDetail;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentResponseDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface StudentMapper {

    Student toRequest(StudentRequest studentRequest);

    @Mapping(source = "user", target = "user")
    StudentResponse toResponse(Student student);

    @Mapping(source = "user", target = "user")
    StudentResponseDetail toResponseDetail(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "student.user", source = "studentRequestDetail.user",qualifiedByName = "updateUserDetailFromRequest")
    void updateStudentFromRequest(@MappingTarget Student student, StudentRequestDetail studentRequestDetail);

}
