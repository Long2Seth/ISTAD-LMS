package co.istad.lms.features.generation;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.generation.dto.GenerationDetailResponse;
import co.istad.lms.features.generation.dto.GenerationRequest;
import co.istad.lms.features.generation.dto.GenerationResponse;
import co.istad.lms.features.generation.dto.GenerationUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/generations")
public class GenerationController {

    private final GenerationService generationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('faculty:write')")
    public void createGeneration(@Valid @RequestBody GenerationRequest generationRequest) {

        generationService.createGeneration(generationRequest);
    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public GenerationDetailResponse getGenerationByAlias(@PathVariable String alias) {

        return generationService.getGenerationByAlias(alias);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<GenerationDetailResponse> getAllGenerations(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return generationService.getAllGenerations(pageNumber, pageSize);
    }

    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    public GenerationDetailResponse updateGeneration(

            @PathVariable String alias,
            @Valid @RequestBody GenerationUpdateRequest generationUpdateRequest
    ) {
        return generationService.updateGenerationByAlias(alias, generationUpdateRequest);

    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void enableGeneration(@PathVariable String alias) {

        generationService.enableGenerationByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('faculty:update')")
    void disableGeneration(@PathVariable String alias) {

        generationService.disableGenerationByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public Page<GenerationDetailResponse> filterGenerations(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return generationService.filterGenerations(filterDto, pageNumber, pageSize);
    }

}
