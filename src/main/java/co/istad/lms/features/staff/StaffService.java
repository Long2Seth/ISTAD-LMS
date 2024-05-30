package co.istad.lms.features.staff;

import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.staff.dto.StaffResponseDetail;
import org.springframework.data.domain.Page;

public interface StaffService {

    StaffResponseDetail createStaff(StaffRequest staffRequest);

    StaffResponseDetail updateStaffByUuid(String uuid, StaffRequestDetail staffRequestDetail);

    StaffResponseDetail getStaffByUuid(String uuid);

    void deleteStaffByUuid(String uuid);

    Page<StaffResponseDetail> getStaffs(int page, int limit);

    StaffResponseDetail disableByUuid(String uuid);

    StaffResponseDetail enableByUuid(String uuid);

    StaffResponseDetail updateDeletedStatus(String uuid);
}
