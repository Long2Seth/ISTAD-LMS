package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Staff;
import co.istad.lms.features.staff.dto.*;
import org.mapstruct.*;
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StaffMapper {

    Staff toRequest(StaffRequest staffRequest);

    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    StaffResponse toResponse(Staff staff);



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
    StaffResponseDetail toResponseDetail(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStaffFromRequest(@MappingTarget Staff staff, StaffRequestUpdate staffRequestUpdate);

}
