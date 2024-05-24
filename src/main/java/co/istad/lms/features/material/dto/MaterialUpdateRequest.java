package co.istad.lms.features.material.dto;

public record MaterialUpdateRequest(

        String alias,
        String title,
        String contentType,
        String extension,
        Long size,
        String fileUrl,
        String thumbnail,
        String description,
        String subjectAlias

) {

}
