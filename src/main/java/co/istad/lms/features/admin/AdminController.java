package co.istad.lms.features.admin;

import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestDetail;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.admin.dto.AdminResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AdminResponse createAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        return adminService.createAdmin(adminRequest);
    }



    @PreAuthorize("hasAnyAuthority('admin:control')")
    @GetMapping("/detail")
    public Page<AdminResponseDetail> findAllDetail(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return adminService.getAdminsDetail(pageNumber, pageSize);
    }

    @PreAuthorize("hasAnyAuthority('admin:control')")
    @GetMapping
    public Page<AdminResponse> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return adminService.getAdmins(pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @GetMapping("/detail/{uuid}")
    public AdminResponseDetail findAdminDetailByUuid(@PathVariable String uuid) {
        return adminService.getAdminDetailByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @GetMapping("/{uuid}")
    public AdminResponse findAdminByUuid(@PathVariable String uuid) {
        return adminService.getAdminByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PutMapping("/{uuid}")
    public AdminResponseDetail updateByUuid(@PathVariable String uuid, @RequestBody AdminRequestDetail adminRequestDetail) {
        return adminService.updateAdminByUuid(uuid, adminRequestDetail);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid) {
        adminService.deleteAdminByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}/disable")
    public AdminResponseDetail disableByUuid(@PathVariable String uuid) {
        return adminService.disableAdminByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}/enable")
    public AdminResponseDetail enableByUuid(@PathVariable String uuid) {
        return adminService.enableAdminByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}/block")
    public AdminResponseDetail blockByUuid(@PathVariable String uuid) {
        return adminService.blockAdminByUuid(uuid);
    }

}
