package co.istad.lms.features.academic;


import co.istad.lms.features.academic.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics")
public class AcademicController {


    private final AcademicService academicService;


    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read')")
    @GetMapping("/detail/{uuid}")
    public AcademicResponseDetail getAcademicDetailByUuid(@PathVariable String uuid) {
        return academicService.getAcademicDetailByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read')")
    @GetMapping("/{uuid}")
    public AcademicResponse getAcademicByUuid(@PathVariable String uuid) {
        return academicService.getAcademicsByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read')")
    @GetMapping
    public Page<AcademicResponse> getAcademics(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return academicService.getAcademicsDetail(pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read')")
    @GetMapping("/detail")
    public Page<AcademicResponseDetail> getAcademicsDetail(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return academicService.getAcademics(pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control' )")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createAcademic(@Valid @RequestBody AcademicRequest academicRequest) {
        academicService.createAcademic(academicRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @PatchMapping("/{uuid}")
    public AcademicResponseDetail updateAcademicByUuid(@PathVariable String uuid, @RequestBody AcademicRequestUpdate academicRequestUpdate) {
        return academicService.updateAcademicByUuid(uuid, academicRequestUpdate);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteAcademicByUuid(@PathVariable String uuid) {
        academicService.deleteAcademicByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}/disable")
    public void updateDisableAcademicByUuid(@PathVariable String uuid) {
        academicService.updateDisableAcademicByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}/enable")
    public void updateEnableAcademicByUuid(@PathVariable String uuid) {
        academicService.updateEnableAcademicByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}/block")
    public void updateDeletedAcademicByUuid(@PathVariable String uuid) {
        academicService.updateDeletedAcademicByUuid(uuid);
    }


}
