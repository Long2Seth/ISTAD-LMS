package co.istad.lms.features.generation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record GenerationUpdateRequest(
        String alias,

        @Size(max = 50, message = "Name must be between 1 and 50 characters")
        String name,

        String description,

        Integer startYear,

        Integer endYear

) {}
