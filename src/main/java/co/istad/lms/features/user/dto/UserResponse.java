package co.istad.lms.features.user.dto;




import co.istad.lms.features.authority.dto.AuthorityResponse;
import co.istad.lms.features.authority.dto.AuthorityResponseToUser;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String gender,
        String email,
        String profileImage,
        String phoneNumber,
        List<AuthorityResponseToUser> authorities
        ){
}
