package co.istad.lms.features.user.dto;

import co.istad.lms.domain.Role;
import java.util.List;

public record UserResponse(
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
        List<Role> roles
        ){
}
