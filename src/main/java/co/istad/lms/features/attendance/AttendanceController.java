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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendances")
public class AttendanceController {

        private final AttendanceService attendanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void creatDegree(@Valid @RequestBody AttendanceRequest attendanceRequest) {

        attendanceService.createAttendance(attendanceRequest);

    }

    @GetMapping("/{uuid}")
    AttendanceDetailResponse getDegreeByUuid(@PathVariable String uuid) {

        return attendanceService.getAttendanceByUuid(uuid);

    }

    @GetMapping
    public Page<AttendanceDetailResponse> getAllAttendances(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return attendanceService.getAllAttendances(page, size);
    }

    @PutMapping("/{uuid}")
    public AttendanceResponse updateAttendance(@PathVariable String uuid,
                                           @Valid @RequestBody AttendanceUpdateRequest attendanceUpdateRequest) {

        return attendanceService.updateAttendanceByUuid(uuid, attendanceUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    public void deleteAttendance(@PathVariable String uuid) {

        attendanceService.deleteAttendanceByUuid(uuid);
    }

    @PatchMapping("/{uuid}/enable")
    void enableAttendance(@PathVariable String uuid){

        attendanceService.enableAttendanceByUuid(uuid);
    }

    @PatchMapping("/{uuid}/disable")
    void disableAttendance(@PathVariable String uuid){

        attendanceService.disableAttendanceByUuid(uuid);
    }

    @GetMapping("/filter")
    public Page<AttendanceDetailResponse> filterAttendances(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return attendanceService.filterAttendances(filterDto, page, size);
    }


}
