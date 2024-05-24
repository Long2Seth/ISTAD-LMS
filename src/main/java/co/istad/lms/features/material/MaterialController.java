package co.istad.lms.features.material;

import co.istad.lms.base.BaseSpecification;
import co.istad.lms.features.material.dto.MaterialDetailResponse;
import co.istad.lms.features.material.dto.MaterialRequest;
import co.istad.lms.features.material.dto.MaterialResponse;
import co.istad.lms.features.material.dto.MaterialUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/materials")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void creatMaterial(@Valid @RequestBody MaterialRequest materialRequest) {

        materialService.createMaterial(materialRequest);

    }

    @GetMapping("/{alias}")
    MaterialDetailResponse getMaterialByAlias(@PathVariable String alias) {

        return materialService.getMaterialByAlias(alias);

    }

    @GetMapping
    public Page<MaterialDetailResponse> getAllMaterials(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return materialService.getAllMaterials(page, size);
    }


    @PutMapping("/{alias}")
    public MaterialResponse updateDegree(@PathVariable String alias,
                                         @Valid @RequestBody MaterialUpdateRequest materialUpdateRequest) {

        return materialService.updateMaterialByAlias(alias, materialUpdateRequest);
    }


    @DeleteMapping("/{alias}")
    public void deleteMaterial(@PathVariable String alias) {

        materialService.deleteMaterialByAlias(alias);
    }

    @PatchMapping("/{alias}/enable")
    void enableMaterial(@PathVariable String alias){

        materialService.enableMaterialByAlias(alias);
    }

    @PatchMapping("/{alias}/disable")
    void disableMaterial(@PathVariable String alias){

        materialService.disableMaterialByAlias(alias);
    }

    @GetMapping("/filter")
    public Page<MaterialDetailResponse> filterMaterials(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return materialService.filterMaterials(filterDto, page, size);
    }



}
