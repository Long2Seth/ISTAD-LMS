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
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmission(@Valid @RequestBody AdmissionCreateRequest admissionCreateRequest) {

        admissionService.createAdmission(admissionCreateRequest);

    }

    @GetMapping("/{uuid}")
    public AdmissionDetailResponse getAdmissionByUuid(@PathVariable String uuid) {

        return admissionService.getAdmissionByUuid(uuid);
    }


    @GetMapping
    public Page<AdmissionResponse> getAllAdmissions(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return admissionService.getAllAdmissions(page, size);
    }


    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public AdmissionResponse updateAdmission(

            @PathVariable String uuid,
            @Valid @RequestBody AdmissionUpdateRequest admissionRequest) {

        return admissionService.updateAdmission(uuid, admissionRequest);
    }

    @PatchMapping("/enable")
    void enableAdmission(String admissionUuid){

        admissionService.enableAdmissionByUuid(admissionUuid);
    }

    @PatchMapping("/disable")
    void disableAdmission(String admissionUuid){

        admissionService.disableAdmissionByUuid(admissionUuid);
    }

    @DeleteMapping("/{uuid}")
    public void deleteAdmission(@PathVariable String uuid) {

        admissionService.deleteAdmission(uuid);

    }

    @GetMapping("/filter")
    public Page<AdmissionDetailResponse> filterAdmissions(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return admissionService.filterAdmissions(filterDto, page, size);
    }
}
