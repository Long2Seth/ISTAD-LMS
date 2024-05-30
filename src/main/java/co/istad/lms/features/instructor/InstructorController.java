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
public class InstructorController {

    private final InstructorService instructorService;

    @PreAuthorize("hasAuthority('admin:control')")
    @PostMapping
    public InstructorResponseDetail createInstructor(@Valid @RequestBody InstructorRequest instructorRequest){
        return instructorService.createInstructor(instructorRequest);
    }

    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read','instructor:read')")
    @GetMapping("/{uuid}")
    public InstructorResponseDetail getInstructorByUuid(@PathVariable String uuid){
        return instructorService.getInstructorByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public InstructorResponseDetail updateInstructorByUuid(@PathVariable String uuid, @RequestBody InstructorRequestDetail instructorRequestDetail){
        return instructorService.updateInstructorByUuid(uuid, instructorRequestDetail);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @DeleteMapping("/{uuid}")
    public void deleteInstructorByUuid(@PathVariable String uuid){
        instructorService.deleteInstructorByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public InstructorResponseDetail disableInstructorByUuid(@PathVariable String uuid){
        return instructorService.disableInstructorByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public InstructorResponseDetail enableInstructorByUuid(@PathVariable String uuid){
        return instructorService.enableInstructorByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/block")
    public InstructorResponseDetail blockInstructorByUuid(@PathVariable String uuid){
        return instructorService.blockInstructorByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping
    public Page<InstructorResponseDetail> getAllInstructor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit
    ){
        return instructorService.getAllInstructor(page, limit);
    }
}
