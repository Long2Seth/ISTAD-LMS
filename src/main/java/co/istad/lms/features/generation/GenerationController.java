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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/generations")
public class GenerationController {

    private final GenerationService generationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createGeneration(@Valid @RequestBody GenerationRequest generationRequest) {

        generationService.createGeneration(generationRequest);
    }

    @GetMapping("/{alias}")
    public GenerationDetailResponse getGenerationByAlias(@PathVariable String alias) {

        return generationService.getGenerationByAlias(alias);
    }

    @GetMapping()
    public Page<GenerationDetailResponse> getAllGenerations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return generationService.getAllGenerations(page, size);
    }

    @PutMapping("/{alias}")
    public GenerationResponse updateGeneration(

            @PathVariable String alias,
            @Valid @RequestBody GenerationUpdateRequest generationUpdateRequest
    ) {
        return generationService.updateGenerationByAlias(alias,generationUpdateRequest);

    }

    @PatchMapping("/{alias}/enable")
    void enableGeneration(@PathVariable String alias){

        generationService.enableGenerationByAlias(alias);
    }

    @PatchMapping("/{alias}/disable")
    void disableGeneration(@PathVariable String alias){

        generationService.disableGenerationByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<GenerationDetailResponse> filterGenerations(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return generationService.filterGenerations(filterDto, page, size);
    }

}
