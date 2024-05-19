package co.istad.lms.features.admission;

import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmissionService {
    AdmissionResponse createAdmission(AdmissionCreateRequest admissionCreateRequest);

    AdmissionResponse getAdmissionByUuid(String uuid);

    List<AdmissionResponse> getAdmissionByNameEn(String nameEn);

    List<AdmissionResponse> getAdmissionByNameKh(String nameKh);

    Page<AdmissionResponse> getAdmissionByNameEnContains(String nameEnContain, Pageable pageable);

    Page<AdmissionResponse> getAdmissionByNameKhContains(String nameKhContain, Pageable pageable);

    Page<AdmissionResponse> getAllAdmissions(Pageable pageable);

    AdmissionResponse updateAdmission(String admissionUuid, AdmissionUpdateRequest admissionRequest);

    void deleteAdmission(String admissionUuid);
}
