package co.istad.lms.mapper;

import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    Authority fromAuthorityRequest(AuthorityRequest authorityRequest);
    AuthorityResponse toAuthorityResponse(Authority authority);
}
