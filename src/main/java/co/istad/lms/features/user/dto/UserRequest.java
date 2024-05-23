package co.istad.lms.features.user.dto;



import co.istad.lms.features.authority.dto.AuthorityRequestToUser;
import lombok.Builder;
import java.util.List;


@Builder
public record UserRequest
        (
                String alias,
                String nameEn,
                String nameKh,
                String username,
                String gender,
                String email,
                String password,
                String profileImage,
                String phoneNumber,

                String cityOrProvince,
                String khanOrDistrict,
                String sangkatOrCommune,
                String street,

                JsonBirthPlace birthPlace,

                List<AuthorityRequestToUser> authorities
        ) {
}
