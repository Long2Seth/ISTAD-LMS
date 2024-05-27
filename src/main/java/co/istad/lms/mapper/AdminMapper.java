package co.istad.lms.mapper;


import co.istad.lms.domain.roles.Admin;
import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin toRequestAdmin(AdminRequest adminRequest);

    @Mapping(source = "user", target = "users")
    AdminResponse toAdminResponse(Admin admin);
}
