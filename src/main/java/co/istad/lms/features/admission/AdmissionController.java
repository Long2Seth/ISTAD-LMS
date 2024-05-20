package co.istad.lms.features.admission;

import co.istad.lms.features.admission.dto.AdmissionCreateRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping("/")
    public ResponseEntity<AdmissionResponse> createNewAdmission(@Valid @RequestBody AdmissionCreateRequest admissionCreateRequest) {
        AdmissionResponse createdAdmission = admissionService.createAdmission(admissionCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmission);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AdmissionDetailResponse> getAdmissionById(@PathVariable String uuid) {
        AdmissionDetailResponse admissionResponse = admissionService.getAdmissionByUuid(uuid);
        return ResponseEntity.ok(admissionResponse);
    }

    @GetMapping("/name-en/{nameEn}")
    public ResponseEntity<List<AdmissionResponse>> getAdmissionByNameEn(@PathVariable String nameEn) {
        List<AdmissionResponse> admissionResponse = admissionService.getAdmissionByNameEn(nameEn);
        return ResponseEntity.ok(admissionResponse);
    }

    @GetMapping("/name-kh/{nameKh}")
    public ResponseEntity<List<AdmissionResponse>> getAdmissionByNameKh(@PathVariable String nameKh) {
        List<AdmissionResponse> admissionResponse = admissionService.getAdmissionByNameKh(nameKh);
        return ResponseEntity.ok(admissionResponse);
    }

    @GetMapping("/name-en-contain/{nameEnContain}")
    public ResponseEntity<Page<AdmissionResponse>> getAdmissionByNameEnContains(
            @PathVariable String nameEnContain,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<AdmissionResponse> admissionResponse = admissionService.getAdmissionByNameEnContains(nameEnContain, pageable);
        return ResponseEntity.ok(admissionResponse);
    }

    @GetMapping("/name-kh-contain/{nameKhContain}")
    public ResponseEntity<Page<AdmissionResponse>> getAdmissionByNameKhContains(
            @PathVariable String nameKhContain,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<AdmissionResponse> admissionResponse = admissionService.getAdmissionByNameKhContains(nameKhContain, pageable);
        return ResponseEntity.ok(admissionResponse);
    }


    @GetMapping("/")
    public ResponseEntity<Page<AdmissionResponse>> getAllAdmissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<AdmissionResponse> admissionsPage = admissionService.getAllAdmissions(pageable);
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
}
