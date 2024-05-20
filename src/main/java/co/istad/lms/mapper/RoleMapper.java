package co.istad.lms.mapper;

import co.istad.lms.domain.Role;
import co.istad.lms.features.role.dto.RoleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    Role toRequestRole(RoleResponse roleResponse);
    RoleResponse toRoleResponse(Role role);



}
