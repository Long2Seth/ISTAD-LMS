package co.istad.lms.mapper;

import co.istad.lms.domain.Material;
import co.istad.lms.features.material.dto.MaterialDetailResponse;
import co.istad.lms.features.material.dto.MaterialRequest;
import co.istad.lms.features.material.dto.MaterialResponse;
import co.istad.lms.features.material.dto.MaterialUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    Material fromMaterialRequest(MaterialRequest materialRequest);

    MaterialDetailResponse toMaterialDetailResponse(Material material);

    MaterialResponse toMaterialResponse(Material material);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMaterialFromRequest(@MappingTarget Material material, MaterialUpdateRequest materialUpdateRequest);

}
