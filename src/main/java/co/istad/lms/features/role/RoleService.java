package co.istad.lms.features.role;

import co.istad.lms.features.role.dto.RoleRequest;
import co.istad.lms.features.role.dto.RoleResponse;
import org.springframework.data.domain.Page;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);

    RoleResponse updateRole(Long id, RoleRequest roleRequest);

    void deleteRole(Long id);

    RoleResponse getRole(Long id);

    Page<RoleResponse> getRoles(int page, int limit);


}
