package co.istad.lms.features.user.dto;



import lombok.Builder;

import java.time.LocalDate;


@Builder
public record UserResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String email,
        String phoneNumber,
        LocalDate dob,
        String gender,
        String profileImage
        ){
}
