package co.istad.lms.features.staff;


import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staffs")
public class StaffController {

    private final StaffService staffService;


    @PreAuthorize("hasAuthority('admin:control')")
    @PostMapping
    public StaffResponseDetail createStaff(@Valid  @RequestBody StaffRequest staffRequest) {
        return staffService.createStaff(staffRequest);
    }



    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping
    public Page<StaffResponseDetail> getStaffs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit)
    {
        return staffService.getStaffs(page, limit);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read','staff:read')")
    @GetMapping("/{uuid}")
    public StaffResponseDetail getStaffByUuid(@PathVariable String uuid) {
        return staffService.getStaffByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public StaffResponseDetail updateStaffByUuid(@PathVariable String uuid, @RequestBody StaffRequestDetail staffRequestDetail) {
        return staffService.updateStaffByUuid(uuid, staffRequestDetail);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @DeleteMapping("/{uuid}")
    public void deleteStaffByUuid(@PathVariable String uuid) {
        staffService.deleteStaffByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public StaffResponseDetail disableByUuid(@PathVariable String uuid) {
        return staffService.disableByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public StaffResponseDetail enableByUuid(@PathVariable String uuid) {
        return staffService.enableByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/block")
    public StaffResponseDetail updateDeletedStatus(@PathVariable String uuid) {
        return staffService.updateDeletedStatus(uuid);
    }


}
