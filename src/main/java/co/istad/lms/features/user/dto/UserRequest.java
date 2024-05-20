package co.istad.lms.features.user.dto;


import co.istad.lms.features.role.dto.RoleRequestToUser;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


@Builder
public record UserRequest
        (
                String name_en,
                String name_kh,
                String userName,
                String gender,
                String email,
                String password,
                String profileImage,
                String phoneNumber,

                String cityOrProvince,
                String khanOrDistrict,
                String sangkatOrCommune,
                String street,

                List<RoleRequestToUser> roles,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                Boolean isDeleted,
                Boolean isBlocked,
                LocalDateTime createdAt
        ) {
}
