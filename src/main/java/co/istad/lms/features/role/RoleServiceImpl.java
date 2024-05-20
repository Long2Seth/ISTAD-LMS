package co.istad.lms.features.role;

import co.istad.lms.domain.Role;
import co.istad.lms.features.role.dto.RoleRequest;
import co.istad.lms.features.role.dto.RoleResponse;
import co.istad.lms.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService{


    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        return null;
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest roleRequest) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public RoleResponse getRole(Long id) {
        return null;
    }

    @Override
    public Page<RoleResponse> getRoles(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Role> roles = roleRepository.findAll(pageRequest);
        return roles.map(roleMapper::toRoleResponse);
    }
}
