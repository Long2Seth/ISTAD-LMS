package co.istad.lms.features.admin;

import co.istad.lms.features.admin.dto.AdminRequest;
import co.istad.lms.features.admin.dto.AdminResponse;
import co.istad.lms.features.auth.dto.AuthRequest;
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
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @Operation(summary = "Create a new admin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "highSchool": "string",
                                              "highSchoolGraduationDate": "2010-05-23",
                                              "degree": "string",
                                              "degreeGraduationDate": "2014-05-23",
                                              "major": "string",
                                              "studyAtUniversityOrInstitution": "string",
                                              "experienceAtWorkingPlace": "string",
                                              "experienceYear": "2020-05-23",
                                              "status": true,
                                              "isDeleted": false,
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
                                                  "villageOrPhum":"string",
                                                  "street":"string",
                                                  "houseNumber":"string"
                                                },
                                                "authorities": [
                                                  {
                                                    "authorityName": "user:read"
                                                  },
                                                  {
                                                    "authorityName": "user:write"
                                                  }
                                                ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    public AdminResponse createAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        return adminService.createAdmin(adminRequest);
    }

    @GetMapping
    public Page<AdminResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit) {
        return adminService.getAdmins(page, limit);
    }

    @GetMapping("/{uuid}")
    public AdminResponse findByUuid(@PathVariable String uuid) {
        return adminService.getAdminByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update admin by uuid",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AdminRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "highSchool": "string1",
                                              "highSchoolGraduationDate": "2010-05-23",
                                              "degree": "string1",
                                              "degreeGraduationDate": "2014-05-23",
                                              "major": "string1",
                                              "studyAtUniversityOrInstitution": "string1",
                                              "experienceAtWorkingPlace": "string1",
                                              "experienceYear": "2020-05-23",
                                              "status": true,
                                              "isDeleted": false,
                                              "userRequest": {
                                                "alias": "john",
                                                "nameEn": "John",
                                                "nameKh": "ជ",
                                                "username": "john",
                                                "gender": "Male",
                                                "email": "john@example.com",
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
                                                  "villageOrPhum":"string",
                                                  "street":"string",
                                                  "houseNumber":"string"
                                                },
                                                "authorities": [
                                                  {
                                                    "authorityName": "user:read"
                                                  },
                                                  {
                                                    "authorityName": "user:write"
                                                  }
                                                ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    public AdminResponse updateByUuid(@PathVariable String uuid, @RequestBody AdminRequest adminRequest) {
        return adminService.updateAdminByUuid(uuid, adminRequest);
    }

    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid) {
        adminService.deleteAdminByUuid(uuid);
    }

    @PatchMapping("/{uuid}/disable")
    public AdminResponse disableByUuid(@PathVariable String uuid) {
        return adminService.disableAdminByUuid(uuid);
    }

    @PatchMapping("/{uuid}/enable")
    public AdminResponse enableByUuid(@PathVariable String uuid) {
        return adminService.enableAdminByUuid(uuid);
    }

    @PatchMapping("/{uuid}/block")
    public AdminResponse blockByUuid(@PathVariable String uuid) {
        return adminService.blockAdminByUuid(uuid);
    }
}
