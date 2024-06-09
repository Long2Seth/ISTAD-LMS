package co.istad.lms.features.instructor;


import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorRequestDetail;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import co.istad.lms.features.instructor.dto.InstructorResponseDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
@PreAuthorize("hasAnyAuthority('admin:control')")
public class InstructorController {

    private final InstructorService instructorService;


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InstructorResponse createInstructor(@Valid @RequestBody InstructorRequest instructorRequest){
        return instructorService.createInstructor(instructorRequest);
    }

    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read','instructor:read')")
    @GetMapping("/detail/{uuid}")
    public InstructorResponseDetail getInstructorDetailByUuid(@PathVariable String uuid){
        return instructorService.getInstructorDetailByUuid(uuid);
    }



    @PreAuthorize("hasAnyAuthority('admin:control' , 'academic:read','instructor:read')")
    @GetMapping("/{uuid}")
    public InstructorResponse getInstructorByUuid(@PathVariable String uuid){
        return instructorService.getInstructorByUuid(uuid);
    }
    



    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public InstructorResponseDetail updateInstructorByUuid(@PathVariable String uuid, @RequestBody InstructorRequestDetail instructorRequestDetail){
        return instructorService.updateInstructorByUuid(uuid, instructorRequestDetail);
    }




    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteInstructorByUuid(@PathVariable String uuid){
        instructorService.deleteInstructorByUuid(uuid);
    }




    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public void disableInstructorByUuid(@PathVariable String uuid){
        instructorService.disableInstructorByUuid(uuid);
    }




    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public void enableInstructorByUuid(@PathVariable String uuid){
         instructorService.enableInstructorByUuid(uuid);
    }




    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/block")
    public void blockInstructorByUuid(@PathVariable String uuid){
         instructorService.blockInstructorByUuid(uuid);
    }




    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping("/detail")
    public Page<InstructorResponseDetail> getAllInstructorDetail(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return instructorService.getAllInstructorDetail(pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping
    public Page<InstructorResponse> getAllInstructor(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ){
        return instructorService.getAllInstructor(search, pageNumber, pageSize);
    }



}
