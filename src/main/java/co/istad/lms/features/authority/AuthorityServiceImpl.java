package co.istad.lms.features.authority;

import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import co.istad.lms.mapper.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j

public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;


    @Override
    public List<AuthorityResponse> findAll() {
        return authorityRepository.findAll()
                .stream()
                .map(authorityMapper::toAuthorityResponse)
                .toList();
    }


    @Override
    public Page<AuthorityResponse> findAll(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Authority> authorities = authorityRepository.findAll(pageRequest);
        return authorities.map(authorityMapper::toAuthorityResponse);
    }

    @Override
    public AuthorityResponse findById(Long id) {
        return null;
    }

    @Override
    public AuthorityResponse update(Long id, AuthorityRequest authorityRequest) {
        return null;
    }

    @Override
    public AuthorityResponse create(AuthorityRequest authorityRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
