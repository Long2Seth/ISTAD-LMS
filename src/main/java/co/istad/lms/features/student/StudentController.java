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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping("/detail")
    public Page<StudentResponseDetail> getStudentsDetail(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return studentService.getStudentsDetail(pageNumber, pageSize);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read')")
    @GetMapping
    public Page<StudentResponse> getStudents(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize) {
        return studentService.getStudents(pageNumber, pageSize);
    }

    @PreAuthorize("hasAuthority('admin:control')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentService.createStudent(studentRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update','user:read')")
    @GetMapping("/detail/{uuid}")
    public StudentResponseDetail getStudentsDetailByUuid(@PathVariable String uuid) {
        return studentService.getStudentDetailByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:read','user:read')")
    @GetMapping("/{uuid}")
    public StudentResponse getStudentByUuid(@PathVariable String uuid) {
        return studentService.getStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PutMapping("/{uuid}")
    public StudentResponseDetail updateStudent(@PathVariable String uuid, @RequestBody StudentRequestDetail studentRequest) {
       return studentService.updateStudentByUuid(uuid, studentRequest);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/enable")
    public void enableStudent(@PathVariable String uuid) {
        studentService.enableStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")
    @PatchMapping("/{uuid}/disable")
    public void disableStudent(@PathVariable String uuid) {
         studentService.disableStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteStudent(@PathVariable String uuid) {
        studentService.deleteStudentByUuid(uuid);
    }


    @PreAuthorize("hasAnyAuthority('admin:control','academic:update')")

    @PatchMapping("/{uuid}/block")
    public void blockStudent(@PathVariable String uuid) {
        studentService.blockStudentByUuid(uuid);
    }


}
