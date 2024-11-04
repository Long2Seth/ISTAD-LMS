package co.istad.lms.features.user.dto;

import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.dto.AuthorityResponseToUser;

import java.util.Set;

public record AuthorityResponse(


        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String email,
        Set<AuthorityResponseToUser> authorities


) {
}
