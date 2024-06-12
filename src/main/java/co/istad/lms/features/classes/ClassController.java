package co.istad.lms.features.classes;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.classes.dto.*;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classes")
public class ClassController {

    private final ClassService classService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('class:write')")
    void creatClass(@Valid @RequestBody ClassRequest classRequest) {

        classService.createClass(classRequest);

    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('class:read')")
    ClassDetailResponse getClassByAlias(@PathVariable String uuid) {

        return classService.getClassByUuid(uuid);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('class:read')")
    public Page<ClassDetailResponse> getAllClasses(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return classService.getAllClasses(pageNumber, pageSize);
    }

    @PatchMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('class:update')")
    public ClassDetailResponse updateClass(@PathVariable String uuid,
                                           @Valid @RequestBody ClassUpdateRequest classUpdateRequest) {

        return classService.updateClassByUuid(uuid, classUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    public void deleteClass(@PathVariable String uuid) {

        classService.deleteClassByUuid(uuid);
    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    void enableClass(@PathVariable String uuid) {

        classService.enableClassByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    void disableClass(@PathVariable String uuid) {

        classService.disableClassByUuid(uuid);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('class:read')")
    public Page<ClassDetailResponse> filterClasses(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return classService.filterClasses(filterDto, pageNumber, pageSize);
    }

    @PostMapping("/{uuid}/students")
    @PreAuthorize("hasAnyAuthority('class:update')")
    public ClassDetailResponse addStudentToClass(@PathVariable String uuid,
                                                 @Valid @RequestBody ClassAddStudentRequest classAddStudentRequest){
        return classService.addStudent(uuid,classAddStudentRequest);
    }

    @DeleteMapping("/{uuid}/students/{suuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    public void deleteStudentInClass(@PathVariable String uuid,@PathVariable String suuid){

        classService.deleteStudent(uuid,suuid);
    }

    @PutMapping("/{uuid}/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:update')")
    void publicClass(@PathVariable String uuid) {

        classService.publicClassByUuid(uuid);
    }

    @PutMapping("/{uuid}/draft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:update')")
    void draftClass(@PathVariable String uuid) {

        classService.draftClassByUuid(uuid);
    }

}
