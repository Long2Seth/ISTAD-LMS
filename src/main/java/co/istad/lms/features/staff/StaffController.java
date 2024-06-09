package co.istad.lms.features.staff;


import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staffs")
public class StaffController {

    private final StaffService staffService;


    @PreAuthorize("hasAuthority('admin:control')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createStaff(@Valid  @RequestBody StaffRequest staffRequest) {
        staffService.createStaff(staffRequest);
    }



    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping("/detail")
    public Page<StaffResponseDetail> getStaffDetail(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize)
    {
        return staffService.getStaffDetail(pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping
    public Page<StaffResponse> getStaffs(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize)
    {
        return staffService.getStaff( pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read','staff:read')")
    @GetMapping("/detail/{uuid}")
    public StaffResponseDetail getStaffDetailByUuid(@PathVariable String uuid) {
        return staffService.getStaffDetailByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read','staff:read')")
    @GetMapping("/{uuid}")
    public StaffResponse getStaffByUuid(@PathVariable String uuid) {
        return staffService.getStaffByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public StaffResponseDetail updateStaffByUuid(@PathVariable String uuid, @RequestBody StaffRequestDetail staffRequestDetail) {
        return staffService.updateStaffByUuid(uuid, staffRequestDetail);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteStaffByUuid(@PathVariable String uuid) {
        staffService.deleteStaffByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public void disableByUuid(@PathVariable String uuid) {
         staffService.disableByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public void enableByUuid(@PathVariable String uuid) {
         staffService.enableByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/block")
    public void updateDeletedStatus(@PathVariable String uuid) {
        staffService.updateDeletedStatus(uuid);
    }


}
