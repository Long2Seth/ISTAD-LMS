package co.istad.lms.features.staff;


import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staffs")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public StaffResponseDetail createStaff(@Valid  @RequestBody StaffRequest staffRequest) {
        return staffService.createStaff(staffRequest);
    }

    @GetMapping
    public Page<StaffResponseDetail> getStaffs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit)
    {
        return staffService.getStaffs(page, limit);
    }

    @GetMapping("/{uuid}")
    public StaffResponseDetail getStaffByUuid(@PathVariable String uuid) {
        return staffService.getStaffByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public StaffResponseDetail updateStaffByUuid(@PathVariable String uuid, @RequestBody StaffRequestDetail staffRequestDetail) {
        return staffService.updateStaffByUuid(uuid, staffRequestDetail);
    }

    @DeleteMapping("/{uuid}")
    public void deleteStaffByUuid(@PathVariable String uuid) {
        staffService.deleteStaffByUuid(uuid);
    }

    @PatchMapping("/{uuid}/disable")
    public StaffResponseDetail disableByUuid(@PathVariable String uuid) {
        return staffService.disableByUuid(uuid);
    }

    @PatchMapping("/{uuid}/enable")
    public StaffResponseDetail enableByUuid(@PathVariable String uuid) {
        return staffService.enableByUuid(uuid);
    }

    @PatchMapping("/{uuid}/block")
    public StaffResponseDetail updateDeletedStatus(@PathVariable String uuid) {
        return staffService.updateDeletedStatus(uuid);
    }


}
