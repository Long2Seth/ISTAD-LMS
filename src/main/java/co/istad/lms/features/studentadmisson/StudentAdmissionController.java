package co.istad.lms.features.studentadmisson;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionDetailResponse;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionRequest;
import co.istad.lms.features.studentadmisson.dto.StudentAdmissionUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student-admissions")
@RequiredArgsConstructor
public class StudentAdmissionController {

    private final StudentAdmissionService studentAdmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('admission:write')")
    public void createStudentAdmission(@Valid @RequestBody StudentAdmissionRequest studentAdmissionRequest) {
        studentAdmissionService.createStudentAdmission(studentAdmissionRequest);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('admission:read')")
    public StudentAdmissionDetailResponse getStudentAdmissionByUuid(@PathVariable String uuid) {

        return studentAdmissionService.getStudentAdmissionByUuid(uuid);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admission:read')")
    public Page<StudentAdmissionDetailResponse> getStudentAllAdmissions(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return studentAdmissionService.getAllStudentAdmissions(page, size);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('admission:update')")
    public StudentAdmissionDetailResponse updateStudentAdmission(

            @PathVariable String uuid,
            @Valid @RequestBody StudentAdmissionUpdateRequest studentAdmissionUpdateRequest) {

        return studentAdmissionService.updateStudentAdmission(uuid, studentAdmissionUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('admission:delete')")
    public void deleteStudentAdmission(@PathVariable String uuid) {

        studentAdmissionService.deleteStudentAdmission(uuid);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('admission:read')")
    public Page<StudentAdmissionDetailResponse> filterStudentAdmissions(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return studentAdmissionService.filterStudentAdmissions(filterDto, page, size);
    }
}
