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
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createShift(@Valid @RequestBody ShiftRequest shiftRequest) {

        shiftService.createShift(shiftRequest);

    }

    @GetMapping("/{alias}")
    ShiftDetailResponse getShiftByAlias(@PathVariable String alias) {

        return shiftService.getShiftByAlias(alias);

    }

    @GetMapping
    public Page<ShiftDetailResponse> getAllShifts(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return shiftService.getAllShifts(page, size);

    }

    @PutMapping("/{alias}")
    public ShiftResponse updateShift(@PathVariable String alias,
                                     @Valid @RequestBody ShiftUpdateRequest shiftUpdateRequest) {

        return shiftService.updateByAlias(alias, shiftUpdateRequest);

    }


    @DeleteMapping("/{alias}")
    public void deleteShift(@PathVariable String alias) {

        shiftService.deleteShiftByAlias(alias);

    }

    @PatchMapping("/{alias}/enable")
    void enableShift(@PathVariable String alias){

        shiftService.enableShiftByAlias(alias);
    }

    @PatchMapping("/{alias}/disable")
    void disableShift(@PathVariable String alias){

        shiftService.disableShiftByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<ShiftDetailResponse> filterShifts(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return shiftService.filterShift(filterDto, page, size);
    }
}
