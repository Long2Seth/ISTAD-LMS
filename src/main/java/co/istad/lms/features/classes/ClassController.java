package co.istad.lms.features.classes;


import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.classes.dto.ClassDetailResponse;
import co.istad.lms.features.classes.dto.ClassRequest;
import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.classes.dto.ClassUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classes")
public class ClassController {

    private final ClassService classService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void creatClass(@Valid @RequestBody ClassRequest classRequest) {

        classService.createClass(classRequest);

    }

    @GetMapping("/{alias}")
    ClassDetailResponse getClassByAlias(@PathVariable String alias) {

        return classService.getClassByAlias(alias);

    }

    @GetMapping
    public Page<ClassDetailResponse> getAllClasses(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return classService.getAllClasses(page, size);
    }

    @PutMapping("/{alias}")
    public ClassResponse updateClass(@PathVariable String alias,
                                     @Valid @RequestBody ClassUpdateRequest classUpdateRequest) {

        return classService.updateClassByAlias(alias, classUpdateRequest);
    }

    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable String alias) {

        classService.deleteClassByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableClass(@PathVariable String alias) {

        classService.enableClassByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableClass(@PathVariable String alias) {

        classService.disableClassByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<ClassDetailResponse> filterClasses(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return classService.filterClasses(filterDto, page, size);
    }
}
