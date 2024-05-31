package co.istad.lms.features.authority;

import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.dto.AuthorityRequest;
import co.istad.lms.features.authority.dto.AuthorityResponse;
import co.istad.lms.mapper.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j

public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;



    @Override
    public Page<AuthorityResponse> findAll(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "id"));
        Page<Authority> authorities = authorityRepository.findAll(pageRequest);
        return authorities.map(authorityMapper::toAuthorityResponse);
    }

    @Override
    public AuthorityResponse findById(String uuid) {
        Authority authority = authorityRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Authority with uuid = %s have been not found", uuid)
                )
        );
        return authorityMapper.toAuthorityResponse(authority);
    }

    @Override
    public AuthorityResponse update(String uuid, AuthorityRequest authorityRequest) {
        Authority authority = authorityRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Authority with uuid = %s  have been not found", uuid)
                )
        );
        authority.setAuthorityName(authorityRequest.authorityName());
        authority.setDescription(authorityRequest.description());
        authorityRepository.save(authority);
        return authorityMapper.toAuthorityResponse(authority);
    }

    @Override
    public AuthorityResponse create(AuthorityRequest authorityRequest) {
        if (authorityRepository.existsByAuthorityName(authorityRequest.authorityName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Authority with name = %s already exists", authorityRequest.authorityName())
            );
        }
        Authority authority = authorityMapper.toAuthorityRequest(authorityRequest);
        authority.setAuthorityName(authorityRequest.authorityName());
        authority.setDescription(authorityRequest.description());
        authorityRepository.save(authority);

        return authorityMapper.toAuthorityResponse(authority);
    }

    @Override
    public void delete(String uuid) {
        Authority authority = authorityRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Authority with uuid = %s have been not found", uuid)
                )
        );
        authorityRepository.delete(authority);
        authorityMapper.toAuthorityResponse(authority);
    }
}
