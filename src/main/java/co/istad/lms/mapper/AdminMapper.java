package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestDetail;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.admin.dto.AdminResponseDetail;
import org.mapstruct.*;


@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface AdminMapper {

    Admin toRequestAdmin(AdminRequest adminRequest);

    @Mapping(source = "user", target = "user")
    AdminResponse toAdminResponse(Admin admin);


    @Mapping(source = "user", target = "user")
    AdminResponseDetail toAdminResponseDetail(Admin admin);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "admin.user", source = "adminRequestDetail.user",qualifiedByName = "updateUserDetailFromRequest")
    void updateAdminFromRequest( @MappingTarget Admin admin, AdminRequestDetail adminRequestDetail );

}
