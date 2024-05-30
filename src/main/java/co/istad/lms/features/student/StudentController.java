package co.istad.lms.features.student;

import co.istad.lms.features.student.dto.StudentRequest;
import co.istad.lms.features.student.dto.StudentRequestDetail;
import co.istad.lms.features.student.dto.StudentResponse;
import co.istad.lms.features.student.dto.StudentResponseDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping
    public Page<StudentResponseDetail> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit) {
        return studentService.getStudents(page, limit);
    }


    @PreAuthorize("hasAuthority('admin:control')")
    @PostMapping
    public StudentResponseDetail createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update','user:read')")
    @GetMapping("/{uuid}")
    public StudentResponseDetail getStudentByUuid(@PathVariable String uuid) {
        return studentService.getStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public StudentResponseDetail updateStudent(@PathVariable String uuid, @RequestBody StudentRequestDetail studentRequest) {
        return studentService.updateStudentByUuid(uuid, studentRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public StudentResponseDetail enableStudent(@PathVariable String uuid) {
        return studentService.enableStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public StudentResponseDetail disableStudent(@PathVariable String uuid) {
        return studentService.disableStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @DeleteMapping("/{uuid}")
    public void deleteStudent(@PathVariable String uuid) {
        studentService.deleteStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")

    @PatchMapping("/{uuid}/block")
    public StudentResponseDetail blockStudent(@PathVariable String uuid) {
        return studentService.blockStudentByUuid(uuid);
    }


}
