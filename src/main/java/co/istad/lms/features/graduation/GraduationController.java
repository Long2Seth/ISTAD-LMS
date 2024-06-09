package co.istad.lms.features.graduation;


import co.istad.lms.features.graduation.dto.GraduationRequest;
import co.istad.lms.features.graduation.dto.GraduationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/graduations")
public class GraduationController {

    private final GraduationService graduationService;

    @GetMapping
    public Page<GraduationResponse> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return graduationService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{uuid}")
    public GraduationResponse findById(@PathVariable String uuid) {
        return graduationService.findById(uuid);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GraduationResponse createGraduation(@RequestBody GraduationRequest graduationRequest) {
        return graduationService.createGraduation(graduationRequest);
    }


    @PutMapping("/{uuid}")
    public GraduationResponse updateGraduation(@PathVariable String uuid, @RequestBody GraduationRequest graduationRequest) {
        return graduationService.updateGraduation(uuid, graduationRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteGraduation(@PathVariable String uuid) {
        graduationService.deleteGraduation(uuid);
    }

}
