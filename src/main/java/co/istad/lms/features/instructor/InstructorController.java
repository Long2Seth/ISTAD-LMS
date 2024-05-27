package co.istad.lms.features.instructor;


import co.istad.lms.features.instructor.dto.InstructorRequest;
import co.istad.lms.features.instructor.dto.InstructorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    
    private final InstructorService instructorService;
    
    @PostMapping
    public InstructorResponse createInstructor(@RequestBody InstructorRequest instructorRequest){
        return instructorService.createInstructor(instructorRequest);
    }

    @GetMapping("/{uuid}")
    public InstructorResponse getInstructorByUuid(@PathVariable String uuid){
        return instructorService.getInstructorByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public InstructorResponse updateInstructorByUuid(@PathVariable String uuid, @RequestBody InstructorRequest instructorRequest){
        return instructorService.updateInstructorByUuid(uuid, instructorRequest);
    }

    @DeleteMapping("/{uuid}")
    public void deleteInstructorByUuid(@PathVariable String uuid){
        instructorService.deleteInstructorByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    public InstructorResponse disableInstructorByUuid(@PathVariable String uuid){
        return instructorService.disableInstructorByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    public InstructorResponse enableInstructorByUuid(@PathVariable String uuid){
        return instructorService.enableInstructorByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    public InstructorResponse blockInstructorByUuid(@PathVariable String uuid){
        return instructorService.blockInstructorByUuid(uuid);
    }

    @GetMapping
    public Page<InstructorResponse> getAllInstructor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit
    ){
        return instructorService.getAllInstructor(page, limit);
    }
}
