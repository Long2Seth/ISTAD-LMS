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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Page<StudentResponseDetail> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit) {
        return studentService.getStudents(page, limit);
    }


    @PostMapping
    public StudentResponseDetail createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }


    @GetMapping("/{uuid}")
    public StudentResponseDetail getStudentByUuid(@PathVariable String uuid) {
        return studentService.getStudentByUuid(uuid);
    }


    @PutMapping("/{uuid}")
    public StudentResponseDetail updateStudent(@PathVariable String uuid, @RequestBody StudentRequestDetail studentRequest) {
        return studentService.updateStudentByUuid(uuid, studentRequest);
    }


    @PatchMapping("/{uuid}/enable")
    public StudentResponseDetail enableStudent(@PathVariable String uuid) {
        return studentService.enableStudentByUuid(uuid);
    }


    @PatchMapping("/{uuid}/disable")
    public StudentResponseDetail disableStudent(@PathVariable String uuid) {
        return studentService.disableStudentByUuid(uuid);
    }


    @DeleteMapping("/{uuid}")
    public void deleteStudent(@PathVariable String uuid) {
        studentService.deleteStudentByUuid(uuid);
    }


    @PatchMapping("/{uuid}/block")
    public StudentResponseDetail blockStudent(@PathVariable String uuid) {
        return studentService.blockStudentByUuid(uuid);
    }


}
