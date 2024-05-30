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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/materials")
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('material:write')")
    void creatMaterial(@Valid @RequestBody MaterialRequest materialRequest) {

        materialService.createMaterial(materialRequest);

    }

    @GetMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('material:read')")
    MaterialDetailResponse getMaterialByAlias(@PathVariable String alias) {

        return materialService.getMaterialByAlias(alias);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('material:read')")
    public Page<MaterialDetailResponse> getAllMaterials(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return materialService.getAllMaterials(page, size);
    }


    @PutMapping("/{alias}")
    @PreAuthorize("hasAnyAuthority('material:update')")
    public MaterialDetailResponse updateMaterial(@PathVariable String alias,
                                                 @Valid @RequestBody MaterialUpdateRequest materialUpdateRequest) {

        return materialService.updateMaterialByAlias(alias, materialUpdateRequest);
    }


    @DeleteMapping("/{alias}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('material:delete')")
    public void deleteMaterial(@PathVariable String alias) {

        materialService.deleteMaterialByAlias(alias);
    }

    @PutMapping("/{alias}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('material:update')")
    void enableMaterial(@PathVariable String alias) {

        materialService.enableMaterialByAlias(alias);
    }

    @PutMapping("/{alias}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('material:update')")
    void disableMaterial(@PathVariable String alias) {

        materialService.disableMaterialByAlias(alias);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority('material:read')")
    public Page<MaterialDetailResponse> filterMaterials(

            @RequestBody BaseSpecification.FilterDto filterDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {

        return materialService.filterMaterials(filterDto, page, size);
    }


}
