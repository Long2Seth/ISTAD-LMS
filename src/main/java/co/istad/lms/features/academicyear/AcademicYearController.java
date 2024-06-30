package co.istad.lms.features.academicyear;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.academicyear.dto.AcademicYearDetailResponse;
import co.istad.lms.features.academicyear.dto.AcademicYearRequest;
import co.istad.lms.features.academicyear.dto.AcademicYearUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/academic-years")
@RequiredArgsConstructor
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('academic:write')")
    public void createAcademicYear(@Valid @RequestBody AcademicYearRequest academicYearRequest) {

        academicYearService.createAcademicYear(academicYearRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('academic:read')")
    public AcademicYearDetailResponse getAcademicYearByAlias(@PathVariable String alias) {

            return academicYearService.getAcademicYearByAlias(alias);
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('academic:read')")
    public Page<AcademicYearDetailResponse> getAllAcademicYears(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return academicYearService.getAllAcademicYears(pageNumber, pageSize);
    }


    @PatchMapping("/{alias}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('academic:update')")
    public AcademicYearDetailResponse updateAcademicYear(

            @PathVariable String alias,
            @Valid @RequestBody AcademicYearUpdateRequest academicYearUpdateRequest) {

        return academicYearService.updateAcademicYear(alias, academicYearUpdateRequest);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('academic:update')")
    void enableAcademicYear(@PathVariable String alias) {

        academicYearService.enableAcademicYearByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('academic:update')")
    void disableAcademicYearByAlias(@PathVariable String alias) {

        academicYearService.disableAcademicYearByAlias(alias);
    }

//    @PutMapping("/{alias}/status")
//    @PreAuthorize("hasAnyAuthority('academic:update')")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void updateAcademicYearStatus(@PathVariable String alias,
//                        @Valid @RequestBody AcademicYearUpdateStatusRequest academicYearUpdateStatusRequest) {
//
//        academicYearService.updateAcademicYearStatus(alias, academicYearUpdateStatusRequest);
//    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('academic:delete')")
    public void deleteAcademicYear(@PathVariable String alias) {

        academicYearService.deleteAcademicYear(alias);

    }

    @PostMapping("/filter")
    @PreAuthorize("hasAnyAuthority('academic:read')")
    public Page<AcademicYearDetailResponse> filterAcademicYears(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return academicYearService.filterAcademicYear(filterDto, pageNumber, pageSize);
    }
}
