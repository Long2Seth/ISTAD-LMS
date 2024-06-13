package co.istad.lms.features.degree.dto;

import jakarta.validation.constraints.Size;

public record DegreeUpdateRequest(



        @Size(max = 100,message = "alias can not be longer than 100 characters")
        String alias,
        @Size(max = 100, message = "Level can not be longer than 100 characters")
        String level,

        String description
) {
}

