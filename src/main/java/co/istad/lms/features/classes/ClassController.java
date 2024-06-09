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

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('class:read')")
    ClassDetailResponse getClassByAlias(@PathVariable String alias) {

        return classService.getClassByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('class:read')")
    public Page<ClassDetailResponse> getAllClasses(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return classService.getAllClasses(pageNumber, pageSize);
    }

    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('class:update')")
    public ClassDetailResponse updateClass(@PathVariable String alias,
                                           @Valid @RequestBody ClassUpdateRequest classUpdateRequest) {

        return classService.updateClassByAlias(alias, classUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    public void deleteClass(@PathVariable String alias) {

        classService.deleteClassByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    void enableClass(@PathVariable String alias) {

        classService.enableClassByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    void disableClass(@PathVariable String alias) {

        classService.disableClassByAlias(alias);
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

    @PostMapping("/{alias}/students")
    @PreAuthorize("hasAnyAuthority('class:update')")
    public ClassDetailResponse addStudentToClass(@PathVariable String alias,
                                                 @Valid @RequestBody ClassAddStudentRequest classAddStudentRequest){
        return classService.addStudent(alias,classAddStudentRequest);
    }

    @DeleteMapping("/{alias}/students/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:delete')")
    public void deleteStudentInClass(@PathVariable String alias,@PathVariable String uuid){

        classService.deleteStudent(alias,uuid);
    }

    @PutMapping("/{alias}/public")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:update')")
    void publicClass(@PathVariable String alias) {

        classService.publicClassByAlias(alias);
    }

    @PutMapping("/{alias}/draft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('class:update')")
    void draftClass(@PathVariable String alias) {

        classService.draftClassByAlias(alias);
    }

}
