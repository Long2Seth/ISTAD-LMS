package co.istad.lms.mapper;

import co.istad.lms.domain.Payment;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.payment.dto.PaymentRequest;
import co.istad.lms.features.student.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface StudentMapper {

    Student toRequest(StudentRequest studentRequest);


    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    StudentResponse toResponse(Student student);


    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.cityOrProvince", target = "cityOrProvince")
    @Mapping(source = "user.khanOrDistrict", target = "khanOrDistrict")
    @Mapping(source = "user.sangkatOrCommune", target = "sangkatOrCommune")
    @Mapping(source = "user.street", target = "street")
    @Mapping(source = "user.birthPlace", target = "birthPlace")
    StudentResponseDetail toResponseDetail(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromRequest(@MappingTarget Student student, StudentRequestUpdate studentRequestUpdate);

}
