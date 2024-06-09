package co.istad.lms.features.user.dto;



import lombok.Builder;


@Builder
public record UserResponse(
        String uuid,
        String nameEn,
        String username,
        String gender,
        String profileImage
        ){
}
