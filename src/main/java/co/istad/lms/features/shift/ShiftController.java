package co.istad.lms.features.shift;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.admission.dto.AdmissionDetailResponse;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('shift:write')")
    void createShift(@Valid @RequestBody ShiftRequest shiftRequest) {

        shiftService.createShift(shiftRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('shift:read')")
    ShiftDetailResponse getShiftByAlias(@PathVariable String alias) {

        return shiftService.getShiftByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('shift:read')")
    public Page<ShiftDetailResponse> getAllShifts(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return shiftService.getAllShifts(pageNumber, pageSize);

    }

    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('shift:update')")
    public ShiftDetailResponse updateShift(@PathVariable String alias,
                                     @Valid @RequestBody ShiftUpdateRequest shiftUpdateRequest) {

        return shiftService.updateByAlias(alias, shiftUpdateRequest);

    }


    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('shift:delete')")
    public void deleteShift(@PathVariable String alias) {

        shiftService.deleteShiftByAlias(alias);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('shift:update')")
    void enableShift(@PathVariable String alias) {

        shiftService.enableShiftByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('shift:update')")
    void disableShift(@PathVariable String alias) {

        shiftService.disableShiftByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('shift:read')")
    public Page<ShiftDetailResponse> filterShifts(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return shiftService.filterShifts(filterDto, pageNumber, pageSize);
    }

    @PutMapping("/{alias}/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('shift:update')")
    void publicShift(@PathVariable String alias) {

        shiftService.publicShiftByAlias(alias);
    }

    @PutMapping("/{alias}/draft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('shift:update')")
    void draftShift(@PathVariable String alias) {

        shiftService.draftShiftByAlias(alias);
    }
}
