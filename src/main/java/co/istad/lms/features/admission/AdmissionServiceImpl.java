// AdmissionServiceImpl.java
package co.istad.lms.features.admission;

import co.istad.lms.domain.Admission;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import co.istad.lms.mapper.AdmissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final AdmissionMapper admissionMapper;

    @Override
    public AdmissionResponse createAdmission(AdmissionCreateRequest admissionCreateRequest) {
        Admission admission = admissionMapper.fromAdmissionRequest(admissionCreateRequest);
        admission.setUuid(UUID.randomUUID().toString()); // Generate UUID
        admission = admissionRepository.save(admission);
        return admissionMapper.toAdmissionResponse(admission);
    }

    @Override
    public AdmissionResponse getAdmissionByUuid(String uuid) {
        Admission admission = admissionRepository.findByUuid(uuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Admission with uuid = " + uuid + " doesn't exist ! "));
        return admissionMapper.toAdmissionResponse(admission);
    }

    @Override
    public List<AdmissionResponse> getAdmissionByNameEn(String nameEn) {
        if(!admissionRepository.existsByNameEn(nameEn)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Admission with name = " + nameEn + " doesn't exist ! ");
        }
        List<Admission> admission = admissionRepository.findByNameEnIgnoreCase(nameEn);
        return admission.stream().map(
                admissionMapper::toAdmissionResponse).toList();
    }

    @Override
    public List<AdmissionResponse> getAdmissionByNameKh(String nameKh) {
        if(!admissionRepository.existsByNameKh(nameKh)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Admission with name = " + nameKh + " doesn't exist ! ");
        }
        List<Admission> admission = admissionRepository.findByNameKhIgnoreCase(nameKh);
        return admission.stream().map(
                admissionMapper::toAdmissionResponse).toList();
    }

    @Override
    public Page<AdmissionResponse> getAdmissionByNameEnContains(String nameEnContain, Pageable pageable) {
        Page<Admission> admissionsPage = admissionRepository.findByNameEnContainingIgnoreCase(nameEnContain, pageable);
        return admissionsPage.map(admissionMapper::toAdmissionResponse);
    }

    @Override
    public Page<AdmissionResponse> getAdmissionByNameKhContains(String nameKhContain, Pageable pageable) {
        Page<Admission> admissionsPage = admissionRepository.findByNameKhContainingIgnoreCase(nameKhContain, pageable);
        return admissionsPage.map(admissionMapper::toAdmissionResponse);
    }


    @Override
    public Page<AdmissionResponse> getAllAdmissions(Pageable pageable) {
        Page<Admission> admissionsPage = admissionRepository.findAll(pageable);
        return admissionsPage.map(admissionMapper::toAdmissionResponse);
    }

    @Override
    public AdmissionResponse updateAdmission(String admissionUuid, AdmissionUpdateRequest admissionRequest) {
        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Account with id = " + admissionUuid + " doesn't exist ! "));

        admissionMapper.updateAdmissionFromRequest(admission, admissionRequest);
        admission = admissionRepository.save(admission);
        return admissionMapper.toAdmissionResponse(admission);
    }

    @Override
    public void deleteAdmission(String admissionUuid) {
        Admission admission = admissionRepository.findByUuid(admissionUuid)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Account with id = " + admissionUuid + " doesn't exist ! "));
        admissionRepository.delete(admission);
    }
}
