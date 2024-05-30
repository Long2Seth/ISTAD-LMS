package co.istad.lms.features.attendance;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.attendance.dto.AttendanceDetailResponse;
import co.istad.lms.features.attendance.dto.AttendanceRequest;
import co.istad.lms.features.attendance.dto.AttendanceResponse;
import co.istad.lms.features.attendance.dto.AttendanceUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('admin:control')")
    void creatAttendance(@Valid @RequestBody AttendanceRequest attendanceRequest) {

        attendanceService.createAttendance(attendanceRequest);

    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    AttendanceDetailResponse getAttendanceByUuid(@PathVariable String uuid) {

        return attendanceService.getAttendanceByUuid(uuid);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<AttendanceDetailResponse> getAllAttendances(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return attendanceService.getAllAttendances(page, size);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public AttendanceResponse updateAttendance(@PathVariable String uuid,
                                               @Valid @RequestBody AttendanceUpdateRequest attendanceUpdateRequest) {

        return attendanceService.updateAttendanceByUuid(uuid, attendanceUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttendance(@PathVariable String uuid) {

        attendanceService.deleteAttendanceByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableAttendance(@PathVariable String uuid) {

        attendanceService.enableAttendanceByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableAttendance(@PathVariable String uuid) {

        attendanceService.disableAttendanceByUuid(uuid);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('admin:control')")
    public Page<AttendanceDetailResponse> filterAttendances(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return attendanceService.filterAttendances(filterDto, page, size);
    }


}
