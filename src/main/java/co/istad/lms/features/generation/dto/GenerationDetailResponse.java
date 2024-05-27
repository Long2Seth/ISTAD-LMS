package co.istad.lms.features.generation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenerationDetailResponse (
        String alias,

        String name,

        String description,

        Integer startYear,

        Integer endYear
){
}
