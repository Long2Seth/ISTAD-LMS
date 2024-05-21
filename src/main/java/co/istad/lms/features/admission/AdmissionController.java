package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.domain.Admission;
import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping
    public ResponseEntity<Void> createNewAdmission(@Valid @RequestBody AdmissionCreateRequest admissionCreateRequest) {

        admissionService.createAdmission(admissionCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AdmissionDetailResponse> getAdmissionByUuid(@PathVariable String uuid) {

        AdmissionDetailResponse admissionResponse = admissionService.getAdmissionByUuid(uuid);

        return ResponseEntity.ok(admissionResponse);
    }


    @GetMapping
    public ResponseEntity<Page<AdmissionResponse>> getAllAdmissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<AdmissionResponse> admissionsPage = admissionService.getAllAdmissions(page,size);

        return ResponseEntity.ok(admissionsPage);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<AdmissionResponse> updateAdmission(@PathVariable String uuid,
                                                             @RequestBody AdmissionUpdateRequest admissionRequest) {

        AdmissionResponse updatedAdmission = admissionService.updateAdmission(uuid, admissionRequest);

        return ResponseEntity.ok(updatedAdmission);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable String uuid) {

        admissionService.deleteAdmission(uuid);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public Page<AdmissionDetailResponse> filterAdmissions(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {

        return admissionService.filterAdmissions(filterDto,page,size);
    }
}
