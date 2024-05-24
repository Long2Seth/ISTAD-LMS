package co.istad.lms.mapper;

import co.istad.lms.domain.Generation;
import co.istad.lms.features.generation.dto.GenerationDetailResponse;
import co.istad.lms.features.generation.dto.GenerationRequest;
import co.istad.lms.features.generation.dto.GenerationResponse;
import co.istad.lms.features.generation.dto.GenerationUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface GenerationMapper {

    Generation fromGenerationRequest(GenerationRequest generationRequest);

    GenerationDetailResponse toGenerationDetailResponse(Generation generation);

    GenerationResponse toGenerationResponse(Generation generation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGenerationFromRequest(@MappingTarget Generation generation, GenerationUpdateRequest generationUpdateRequest);
}
