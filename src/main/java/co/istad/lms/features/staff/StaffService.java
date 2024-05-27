package co.istad.lms.features.staff;

import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffResponse;
import org.springframework.data.domain.Page;

public interface StaffService {

    StaffResponse createStaff(StaffRequest staffRequest);

    StaffResponse updateStaffByUuid(String uuid, StaffRequest staffRequest);

    StaffResponse getStaffByUuid(String uuid);

    void deleteStaffByUuid(String uuid);

    Page<StaffResponse> getStaffs(int page, int limit);

    StaffResponse disableByUuid(String uuid);

    StaffResponse enableByUuid(String uuid);

    StaffResponse updateDeletedStatus(String uuid);
}
