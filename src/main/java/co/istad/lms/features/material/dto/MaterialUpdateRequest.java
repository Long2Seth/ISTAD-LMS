package co.istad.lms.features.material.dto;

public record MaterialUpdateRequest(

        String alias,
        String title,
        String fileUrl,
        String thumbnail,
        String description

) {

}
