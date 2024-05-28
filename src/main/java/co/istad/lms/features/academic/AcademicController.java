package co.istad.lms.features.academic;


import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics")
public class AcademicController {

    private final AcademicService academicService;

    @GetMapping("/{uuid}")
    public AcademicResponse getAcademicByUuid( @PathVariable String uuid){
        return academicService.getAcademicByUuid(uuid);
    }

    @GetMapping
    public Page<AcademicResponse> getAcademics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit)
    {
        return academicService.getAcademics(page, limit);
    }

    @PostMapping
    public AcademicResponse createAcademic(@Valid @RequestBody AcademicRequest academicRequest){
        return academicService.createAcademic(academicRequest);
    }

    @PutMapping("/{uuid}")
    public AcademicResponse updateAcademicByUuid(@PathVariable String uuid , @RequestBody AcademicRequest academicRequest){
        return academicService.updateAcademicByUuid(uuid, academicRequest);
    }

    @DeleteMapping("/{uuid}")
    public AcademicResponse deleteAcademicByUuid(@PathVariable String uuid){
        return academicService.deleteAcademicByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    public AcademicResponse updateDisableAcademicByUuid(@PathVariable String uuid){
        return academicService.updateDisableAcademicByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    public AcademicResponse updateEnableAcademicByUuid(@PathVariable String uuid){
        return academicService.updateEnableAcademicByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    public AcademicResponse updateDeletedAcademicByUuid(@PathVariable String uuid){
        return academicService.updateDeletedAcademicByUuid(uuid);
    }
}
