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
    public Page<StudentResponse> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit) {
        return studentService.getStudents(page, limit);
    }

    @Operation(
            summary = "Create a new student",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "userRequest": {
                                                "alias": "john",
                                                "nameEn": "John",
                                                "nameKh": "ជ",
                                                "username": "john",
                                                "gender": "Male",
                                                "email": "john@example1.com",
                                                "password": "password123",
                                                "profileImage": "http://example.com/images/johndoe.jpg",
                                                "phoneNumber": "12345",
                                                "cityOrProvince": "Phnom Penh",
                                                "khanOrDistrict": "Chamkar Mon",
                                                "sangkatOrCommune": "Toul Tompoung",
                                                "street": "Street 123",
                                                "birthPlace": {
                                                  "cityOrProvince": "string",
                                                  "khanOrDistrict": "string",
                                                  "sangkatOrCommune": "string",
                                                  "villageOrPhum": "string",
                                                  "street": "string",
                                                  "houseNumber": "string"
                                                }
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    @PostMapping
    public StudentResponse createStudent(@Valid  @RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }

    @GetMapping("/{uuid}")
    public StudentResponse getStudentByUuid(@PathVariable String uuid) {
        return studentService.getStudentByUuid(uuid);
    }


    @PutMapping("/{uuid}")
    @Operation(summary = "Update student by uuid",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "userRequest": {
                                                    "nameEn": "string",
                                                    "nameKh": "string",
                                                    "username": "string",
                                                    "gender": "string",
                                                    "email": "string",
                                                    "password": "string",
                                                    "profileImage": "string",
                                                    "phoneNumber": "string",
                                                    "cityOrProvince": "string",
                                                    "khanOrDistrict": "string",
                                                    "sangkatOrCommune": "string",
                                                    "street": "string",
                                                    "birthPlace": {
                                                        "cityOrProvince": "string",
                                                        "khanOrDistrict": "string",
                                                        "sangkatOrCommune": "string",
                                                        "villageOrPhum": "string",
                                                        "street": "string",
                                                        "houseNumber": "string"
                                                    }
                                                }
                                            }
                                            """
                            )
                    )
            )
    )
    public StudentResponseDetail updateStudent(@PathVariable String uuid, @RequestBody StudentRequestDetail studentRequest) {
        return studentService.updateStudentByUuid(uuid, studentRequest);
    }


    @PatchMapping("/{uuid}/enable")
    public StudentResponse enableStudent(@PathVariable String uuid) {
        return studentService.enableStudentByUuid(uuid);
    }

    @PatchMapping("/{uuid}/disable")
    public StudentResponse disableStudent(@PathVariable String uuid) {
        return studentService.disableStudentByUuid(uuid);
    }

    @DeleteMapping("/{uuid}")
    public void deleteStudent(@PathVariable String uuid) {
        studentService.deleteStudentByUuid(uuid);
    }

    @PatchMapping("/{uuid}/block")
    public StudentResponse blockStudent(@PathVariable String uuid) {
        return studentService.blockStudentByUuid(uuid);
    }




}
