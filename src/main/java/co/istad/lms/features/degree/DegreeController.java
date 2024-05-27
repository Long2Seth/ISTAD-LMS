package co.istad.lms.features.degree;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/degrees")
public class DegreeController {

    private final DegreeService degreeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void creatDegree(@Valid @RequestBody DegreeRequest degreeRequest) {

        degreeService.createDegree(degreeRequest);

    }

    @GetMapping("/{alias}")
    DegreeDetailResponse getDegreeByAlias(@PathVariable String alias) {

        return degreeService.getDegreeByAlias(alias);

    }

    @GetMapping
    public Page<DegreeDetailResponse> getAllDegrees(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return degreeService.getAllDegrees(page, size);
    }


    @PutMapping("/{alias}")
    public DegreeResponse updateDegree(@PathVariable String alias,
                                       @Valid @RequestBody DegreeUpdateRequest degreeUpdateRequest) {

        return degreeService.updateDegreeByAlias(alias, degreeUpdateRequest);
    }


    @DeleteMapping("/{alias}")
    public void deleteDegree(@PathVariable String alias) {

        degreeService.deleteDegreeByAlias(alias);
    }

    @PatchMapping("/{alias}/enable")
    void enableDegree(@PathVariable String alias){

        degreeService.enableDegreeByAlias(alias);
    }

    @PatchMapping("/{alias}/disable")
    void disableDegree(@PathVariable String alias){

        degreeService.disableDegreeByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<DegreeDetailResponse> filterDegrees(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return degreeService.filterDegrees(filterDto, page, size);
    }

}
