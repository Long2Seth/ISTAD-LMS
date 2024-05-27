package co.istad.lms.features.staff.dto;


import co.istad.lms.features.user.dto.UserResponse;
import lombok.Builder;

@Builder
public record StaffResponse(

        String uuid,
        String position,
        UserResponse userResponse

) {
}
