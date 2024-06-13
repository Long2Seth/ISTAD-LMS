package co.istad.lms.features.generation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record GenerationUpdateRequest(

        @Size(max = 100, message = "name can not be longer than 100 characters")
        String alias,
        @Size(max = 100, message = "name can not be longer than 100 characters")
        String name,

        String description,

        @Positive(message = "startYear must be positive")
        Integer startYear,

        @Positive(message = "endYear must be positive")
        Integer endYear

) {}
