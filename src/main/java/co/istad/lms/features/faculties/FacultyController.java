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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    private final FacultyService facultyService;


    @PreAuthorize("hasAnyAuthority('faculty:write')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createDegree(@Valid @RequestBody FacultyRequest facultyRequest) {

        facultyService.createFaculty(facultyRequest);

    }

    @PreAuthorize("hasAnyAuthority('faculty:read')")
    @GetMapping("/{alias}")
    FacultyDetailResponse getFacultyByAlias(@PathVariable String alias) {

        return facultyService.getFacultyByAlias(alias);
    }


    @PreAuthorize("hasAnyAuthority('faculty:read')")
    @GetMapping
    public Page<FacultyDetailResponse> getAllFaculties(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return facultyService.getAllFaculties(pageNumber, pageSize);

    }

    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public FacultyDetailResponse updateDegree(@PathVariable String alias,
                                              @Valid @RequestBody FacultyUpdateRequest facultyUpdateRequest) {

        return facultyService.updateFacultyByAlias(alias, facultyUpdateRequest);

    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:delete')")
    public void deleteFaculty(@PathVariable String alias) {

        facultyService.deleteFacultyByAlias(alias);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void enableFaculty(@PathVariable String alias) {

        facultyService.enableFacultyByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void disableFaculty(@PathVariable String alias) {

        facultyService.disableFacultyByAlias(alias);
    }


    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<FacultyDetailResponse> filterFaculties(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return facultyService.filterFaculties(filterDto, pageNumber, pageSize);
    }

}
