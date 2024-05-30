package co.istad.lms.features.admission;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.*;
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
    @PreAuthorize("hasAnyAuthority('admission:write')")
    public void createAdmission(@Valid @RequestBody AdmissionRequest admissionCreateRequest) {

        admissionService.createAdmission(admissionCreateRequest);

    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admission:read')")
    public AdmissionDetailResponse getAdmissionByUuid(@PathVariable String uuid) {

        return admissionService.getAdmissionByUuid(uuid);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('admission:read')")
    public Page<AdmissionDetailResponse> getAllAdmissions(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return admissionService.getAllAdmissions(page, size);
    }


    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('admission:update')")
    public AdmissionDetailResponse updateAdmission(

            @PathVariable String uuid,
            @Valid @RequestBody AdmissionUpdateRequest admissionRequest) {

        return admissionService.updateAdmission(uuid, admissionRequest);
    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admission:update')")
    void enableAdmission(@PathVariable String uuid) {

        admissionService.enableAdmissionByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admission:update')")
    void disableAdmission(@PathVariable String uuid) {

        admissionService.disableAdmissionByUuid(uuid);
    }

    @PutMapping("/{uuid}/status")
    @PreAuthorize("hasAnyAuthority('admission:update')")
    void updateAdmissionStatus(@PathVariable String uuid, @Valid @RequestBody AdmissionUpdateStatusRequest admissionUpdateStatusRequest) {

        admissionService.updateAdmissionStatus(uuid, admissionUpdateStatusRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admission:delete')")
    public void deleteAdmission(@PathVariable String uuid) {

        admissionService.deleteAdmission(uuid);

    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('admission:read')")
    public Page<AdmissionDetailResponse> filterAdmissions(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return admissionService.filterAdmissions(filterDto, page, size);
    }
}
