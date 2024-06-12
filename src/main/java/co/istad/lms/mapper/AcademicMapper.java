package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Academic;
import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.academic.dto.*;
import co.istad.lms.features.admin.dto.AdminRequestUpdate;
import org.mapstruct.*;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface AcademicMapper {

    Academic toRequest(AcademicRequest academicRequest);


    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    AcademicResponse toResponse(Academic academic);


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
    AcademicResponseDetail toResponseDetail(Academic academic);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAcademicFromRequest(@MappingTarget Academic academic, AcademicRequestUpdate academicRequestUpdate);

}
