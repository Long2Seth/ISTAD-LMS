package co.istad.lms.features.user.dto;



import co.istad.lms.features.role.dto.RoleResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String alias,
        String name_en,
        String name_kh,
        String email,
        String userName,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        Boolean isBlocked,
        List<RoleResponse> roles
        ){
}
