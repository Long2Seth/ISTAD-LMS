package co.istad.lms.features.admin;

import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminResponse;
import org.springframework.data.domain.Page;

public interface AdminService {

    AdminResponse createAdmin(AdminRequest adminRequest);

    AdminResponse updateAdminByUuid(String uuid , AdminRequest adminRequest);

    Page<AdminResponse> getAdmins(int page, int limit);

    AdminResponse getAdminByUuid(String uuid);

    void deleteAdminByUuid(String uuid);

    AdminResponse disableAdminByUuid(String uuid);

    AdminResponse enableAdminByUuid(String uuid);

    AdminResponse blockAdminByUuid(String uuid);
}
