package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.AdmissionRequest;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.admission.dto.AdmissionResponse;
import co.istad.lms.features.admission.dto.AdmissionUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public void createAdmission(@Valid @RequestBody AdmissionRequest admissionCreateRequest) {

        admissionService.createAdmission(admissionCreateRequest);

    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public AdmissionDetailResponse getAdmissionByUuid(@PathVariable String uuid) {

        return admissionService.getAdmissionByUuid(uuid);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<AdmissionResponse> getAllAdmissions(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return admissionService.getAllAdmissions(page, size);
    }


    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public AdmissionDetailResponse updateAdmission(

            @PathVariable String uuid,
            @Valid @RequestBody AdmissionUpdateRequest admissionRequest) {

        return admissionService.updateAdmission(uuid, admissionRequest);
    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void enableAdmission(@PathVariable String uuid) {

        admissionService.enableAdmissionByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void disableAdmission(@PathVariable String uuid) {

        admissionService.disableAdmissionByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public void deleteAdmission(@PathVariable String uuid) {

        admissionService.deleteAdmission(uuid);

    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<AdmissionDetailResponse> filterAdmissions(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return admissionService.filterAdmissions(filterDto, page, size);
    }
}
