package co.istad.lms.features.material.dto;

import jakarta.validation.constraints.Size;

public record MaterialUpdateRequest(

        String title,

        String fileName,
        String description

) {

}
