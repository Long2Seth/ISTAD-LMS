package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.*;
import org.mapstruct.*;


@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface AdminMapper {


    @Mapping(target = "user.dob" , ignore = true)
    Admin toRequestAdmin(AdminRequest adminRequest);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    AdminResponse toAdminResponse(Admin admin);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    AdminResponseDetail toAdminResponseDetail(Admin admin);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdminFromRequest(@MappingTarget Admin admin, AdminRequestUpdate adminRequestUpdate);

}
