package co.istad.lms.features.degree;


import co.istad.lms.features.degree.dto.DegreeCreateRequest;
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
    ResponseEntity<Void> createNewDegree(@Valid @RequestBody DegreeCreateRequest degreeCreateRequest) {

        degreeService.createDegree(degreeCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{alias}")
    ResponseEntity<DegreeDetailResponse> getDegreeByAlias(@PathVariable String alias) {

        DegreeDetailResponse degreeDetailResponse = degreeService.getDegreeByAlias(alias);

        return ResponseEntity.ok(degreeDetailResponse);
    }

    @GetMapping("/{level}")
    ResponseEntity<DegreeDetailResponse> getDegreeByLevel(@PathVariable String level) {

        DegreeDetailResponse degreeDetailResponse = degreeService.getDegreeByLevel(level);

        return ResponseEntity.ok(degreeDetailResponse);
    }


    @GetMapping
    public ResponseEntity<Page<DegreeDetailResponse>> getAllDegree(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<DegreeDetailResponse> admissionsPage = degreeService.getAllDegrees(page, size);

        return ResponseEntity.ok(admissionsPage);
    }


    @PutMapping("/{alias}")
    public ResponseEntity<DegreeResponse> updateDegree(@PathVariable String alias,
                                                       @RequestBody DegreeUpdateRequest degreeUpdateRequest) {

        DegreeResponse updatedDegree = degreeService.updateDegreeByAlias(alias, degreeUpdateRequest);

        return ResponseEntity.ok(updatedDegree);
    }


    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> deleteDegree(@PathVariable String alias) {

        degreeService.deleteDegreeByAlias(alias);

        return ResponseEntity.noContent().build();
    }


}
