package co.istad.lms.features.faculties;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import co.istad.lms.features.faculties.dto.FacultyRequest;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.faculties.dto.FacultyUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    private final FacultyService facultyService;


    @PostMapping
    ResponseEntity<Void> createNewDegree(@Valid @RequestBody FacultyRequest facultyRequest) {

        facultyService.createNewFaculty(facultyRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{alias}")
    ResponseEntity<FacultyDetailResponse> getFacultyByAlias(@PathVariable String alias) {

        FacultyDetailResponse facultyDetailResponse = facultyService.getFacultyByAlias(alias);

        return ResponseEntity.ok(facultyDetailResponse);
    }

    @GetMapping
    public ResponseEntity<Page<FacultyDetailResponse>> getAllFaculty(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<FacultyDetailResponse> faculties = facultyService.getAllFaculties(page, size);

        return ResponseEntity.ok(faculties);
    }

    @PutMapping("/{alias}")
    public ResponseEntity<FacultyResponse> updateDegree(@PathVariable String alias,
                                                        @RequestBody FacultyUpdateRequest facultyUpdateRequest) {

        FacultyResponse updateFaculty = facultyService.updateFacultyByAlias(alias, facultyUpdateRequest);

        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String alias) {

        facultyService.deleteFacultyByAlias(alias);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/filter")
    public Page<FacultyDetailResponse> filterDegree(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return facultyService.filter(filterDto,page,size);
    }

}
