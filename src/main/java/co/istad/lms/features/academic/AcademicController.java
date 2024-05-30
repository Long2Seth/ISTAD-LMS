package co.istad.lms.features.academic;


import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestDetail;
import co.istad.lms.features.academic.dto.AcademicResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics")
public class AcademicController {

    private final AcademicService academicService;

    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read')")
    @GetMapping("/{uuid}")
    public AcademicResponseDetail getAcademicByUuid(@PathVariable String uuid){
        return academicService.getAcademicByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read')")
    @GetMapping
    public Page<AcademicResponseDetail> getAcademics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit)
    {
        return academicService.getAcademics(page, limit);
    }


    @PreAuthorize("hasAnyAuthority('admin:control' )")
    @PostMapping
    public AcademicResponseDetail createAcademic(@Valid @RequestBody AcademicRequest academicRequest){
        return academicService.createAcademic(academicRequest);
    }

    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PutMapping("/{uuid}")
    public AcademicResponseDetail updateAcademicByUuid(@PathVariable String uuid , @RequestBody AcademicRequestDetail academicRequest){
        return academicService.updateAcademicByUuid(uuid, academicRequest);
    }

    @PreAuthorize("hasAnyAuthority('admin:control')")
    @DeleteMapping("/{uuid}")
    public AcademicResponseDetail deleteAcademicByUuid(@PathVariable String uuid){
        return academicService.deleteAcademicByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}/disable")
    public AcademicResponseDetail updateDisableAcademicByUuid(@PathVariable String uuid){
        return academicService.updateDisableAcademicByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}/enable")
    public AcademicResponseDetail updateEnableAcademicByUuid(@PathVariable String uuid){
        return academicService.updateEnableAcademicByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}/block")
    public AcademicResponseDetail updateDeletedAcademicByUuid(@PathVariable String uuid){
        return academicService.updateDeletedAcademicByUuid(uuid);
    }
}
