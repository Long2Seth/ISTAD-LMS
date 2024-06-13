package co.istad.lms.features.material.dto;

import jakarta.validation.constraints.Size;

public record MaterialUpdateRequest(

        @Size(max = 100,message = "alias can not be longer than 100 characters")
        String alias,
        String title,

        String fileName,
        String description

) {

}
