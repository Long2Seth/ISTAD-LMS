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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/degrees")
public class DegreeController {

    private final DegreeService degreeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('faculty:write')")
    void creatDegree(@Valid @RequestBody DegreeRequest degreeRequest) {

        degreeService.createDegree(degreeRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    DegreeDetailResponse getDegreeByAlias(@PathVariable String alias) {

        return degreeService.getDegreeByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<DegreeDetailResponse> getAllDegrees(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return degreeService.getAllDegrees(pageNumber, pageSize);
    }


    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public DegreeDetailResponse updateDegree(@PathVariable String alias,
                                             @Valid @RequestBody DegreeUpdateRequest degreeUpdateRequest) {

        return degreeService.updateDegreeByAlias(alias, degreeUpdateRequest);
    }


    @DeleteMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDegree(@PathVariable String alias) {

        degreeService.deleteDegreeByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableDegree(@PathVariable String alias) {

        degreeService.enableDegreeByAlias(alias);
    }

    @PutMapping("/{alias}/public")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void publicDegree(@PathVariable String alias) {

        degreeService.publicDegreeByAlias(alias);
    }

    @PutMapping("/{alias}/private")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void privateDegree(@PathVariable String alias) {

        degreeService.privateDegreeByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableDegree(@PathVariable String alias) {

        degreeService.disableDegreeByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<DegreeDetailResponse> filterDegrees(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return degreeService.filterDegrees(filterDto, pageNumber, pageSize);
    }

}
