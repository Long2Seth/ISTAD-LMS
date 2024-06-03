package co.istad.lms.features.material.dto;

public record MaterialUpdateRequest(

        String alias,
        String title,

        String fileName,
        String description

) {

}
