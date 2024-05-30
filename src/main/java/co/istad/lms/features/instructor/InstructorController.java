package co.istad.lms.features.instructor;


import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
@PreAuthorize("hasAnyAuthority('admin:control')")
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public InstructorResponse createInstructor(@Valid @RequestBody InstructorRequest instructorRequest){
        return instructorService.createInstructor(instructorRequest);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public InstructorResponse getInstructorByUuid(@PathVariable String uuid){
        return instructorService.getInstructorByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public InstructorRequestDetail updateInstructorByUuid(@PathVariable String uuid, @RequestBody InstructorRequestDetail instructorRequestDetail){
        return instructorService.updateInstructorByUuid(uuid, instructorRequestDetail);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public void deleteInstructorByUuid(@PathVariable String uuid){
        instructorService.deleteInstructorByUuid(uuid);
    }

    @PatchMapping("/{uuid}/disable")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public InstructorResponse disableInstructorByUuid(@PathVariable String uuid){
        return instructorService.disableInstructorByUuid(uuid);
    }

    @PatchMapping("/{uuid}/enable")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public InstructorResponse enableInstructorByUuid(@PathVariable String uuid){
        return instructorService.enableInstructorByUuid(uuid);
    }

    @PatchMapping("/{uuid}/block")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public InstructorResponse blockInstructorByUuid(@PathVariable String uuid){
        return instructorService.blockInstructorByUuid(uuid);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<InstructorResponse> getAllInstructor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit
    ){
        return instructorService.getAllInstructor(page, limit);
    }
}
