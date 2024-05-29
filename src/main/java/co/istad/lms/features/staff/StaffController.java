package co.istad.lms.features.staff;


import co.istad.lms.features.staff.dto.StaffRequest;
import co.istad.lms.features.staff.dto.StaffRequestDetail;
import co.istad.lms.features.staff.dto.StaffResponse;
import co.istad.lms.features.student.dto.StudentRequest;
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
@RequestMapping("/api/v1/staffs")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    @Operation(summary = "Create a new staff ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StudentRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "position":"string",
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
                                                },
                                                "authorities": [
                                                  {
                                                    "authorityName": "user:read"
                                                  }
                                                ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    )
    public StaffResponse createStaff(@Valid  @RequestBody StaffRequest staffRequest) {
        return staffService.createStaff(staffRequest);
    }

    @GetMapping
    public Page<StaffResponse> getStaffs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int limit)
    {
        return staffService.getStaffs(page, limit);
    }

    @GetMapping("/{uuid}")
    public StaffResponse getStaffByUuid(@PathVariable String uuid) {
        return staffService.getStaffByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public StaffRequestDetail updateStaffByUuid(@PathVariable String uuid, @RequestBody StaffRequestDetail staffRequestDetail) {
        return staffService.updateStaffByUuid(uuid, staffRequestDetail);
    }

    @DeleteMapping("/{uuid}")
    public void deleteStaffByUuid(@PathVariable String uuid) {
        staffService.deleteStaffByUuid(uuid);
    }

    @PatchMapping("/{uuid}/disable")
    public StaffResponse disableByUuid(@PathVariable String uuid) {
        return staffService.disableByUuid(uuid);
    }

    @PatchMapping("/{uuid}/enable")
    public StaffResponse enableByUuid(@PathVariable String uuid) {
        return staffService.enableByUuid(uuid);
    }

    @PatchMapping("/{uuid}/block")
    public StaffResponse updateDeletedStatus(@PathVariable String uuid) {
        return staffService.updateDeletedStatus(uuid);
    }


}
