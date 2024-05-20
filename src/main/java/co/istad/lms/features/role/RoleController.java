package co.istad.lms.features.role;


import co.istad.lms.base.BasedResponse;
import co.istad.lms.features.role.dto.RoleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@Slf4j
public class RoleController {

    private final RoleService roleService;


    @GetMapping("/all-roles")
    public BasedResponse<Page<RoleResponse>> getAllRoles() {
        return BasedResponse.<Page<RoleResponse>>ok()
                .setPayload(roleService.getRoles(0, 2));
    }



}
