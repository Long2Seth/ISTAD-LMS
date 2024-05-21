package co.istad.lms.features.shift;

import co.istad.lms.features.degree.dto.DegreeCreateRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import co.istad.lms.features.shift.dto.ShiftUpdateResponse;
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
    ResponseEntity<Void> createNewShift(@Valid @RequestBody ShiftRequest shiftRequest) {

        shiftService.createNewShift(shiftRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{alias}")
    ResponseEntity<ShiftDetailResponse> getShiftByAlias(@PathVariable String alias) {

        ShiftDetailResponse shiftDetailResponse = shiftService.getShiftByAlias(alias);

        return ResponseEntity.ok(shiftDetailResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ShiftDetailResponse>> getAllShift(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<ShiftDetailResponse> shiftPage = shiftService.getAllShift(page, size);

        return ResponseEntity.ok(shiftPage);
    }

    @PutMapping("/{alias}")
    public ResponseEntity<ShiftUpdateResponse> updateShift(@PathVariable String alias,
                                                            @RequestBody ShiftUpdateRequest shiftUpdateRequest) {

        ShiftUpdateResponse shiftUpdateResponse = shiftService.updateByAlias(alias, shiftUpdateRequest);

        return ResponseEntity.ok(shiftUpdateResponse);
    }



    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> deleteShift(@PathVariable String alias) {

        shiftService.deleteShiftByAlias(alias);

        return ResponseEntity.noContent().build();
    }
}
