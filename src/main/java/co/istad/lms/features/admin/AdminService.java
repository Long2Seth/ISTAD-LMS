package co.istad.lms.features.admin;

import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminRequestDetail;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.admin.dto.AdminResponseDetail;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface AdminService {

    AdminResponse createAdmin(@Valid AdminRequest adminRequest);

    AdminResponseDetail updateAdminByUuid (String uuid, AdminRequestDetail adminRequestDetail);


    Page<AdminResponseDetail> getAdminsDetail(int page, int limit);

    Page<AdminResponse> getAdmins(int page, int limit);

    AdminResponseDetail getAdminDetailByUuid(String uuid);

    AdminResponse  getAdminByUuid(String uuid);

    void deleteAdminByUuid(String uuid);

    AdminResponseDetail disableAdminByUuid(String uuid);

    AdminResponseDetail enableAdminByUuid(String uuid);

    AdminResponseDetail blockAdminByUuid(String uuid);
}
