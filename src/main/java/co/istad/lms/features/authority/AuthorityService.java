package co.istad.lms.features.authority;

import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorityService {

    Page<AuthorityResponse> findAll(int page, int limit);
    AuthorityResponse findById(Long id);
    AuthorityResponse update(Long id, AuthorityRequest authorityRequest);
    AuthorityResponse create(AuthorityRequest authorityRequest);
    AuthorityResponse delete(Long id);


}
