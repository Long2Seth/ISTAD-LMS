package co.istad.lms.features.user.dto;




import co.istad.lms.features.authority.dto.AuthorityResponse;
import co.istad.lms.features.authority.dto.AuthorityResponseToUser;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        String alias,
        String nameEn,
        String nameKh,
        String email,
        String username,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        Boolean isBlocked,

        JsonBirthPlace birthPlace,
        List<AuthorityResponseToUser> authorities
        ){
}
