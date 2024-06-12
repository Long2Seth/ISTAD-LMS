package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.*;
import org.mapstruct.*;


@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface AdminMapper {

    Admin toRequestAdmin(AdminRequest adminRequest);

    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    AdminResponse toAdminResponse(Admin admin);

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
    AdminResponseDetail toAdminResponseDetail(Admin admin);

    AdminRequestUpdate toAdminRequestUpdate(Admin admin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdminFromRequest(@MappingTarget Admin admin, AdminRequestUpdate adminRequestUpdate);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "admin.user", source = "adminRequestDetail.user",qualifiedByName = "updateUserDetailFromRequest")
//    void updateAdminFromRequest( @MappingTarget Admin admin, AdminRequestDetail adminRequestDetail );

}
