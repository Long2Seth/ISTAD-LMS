package co.istad.lms.utils;

import co.istad.lms.domain.Authority;
import co.istad.lms.features.authority.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class DefaultAuthority {

    private final AuthorityRepository authorityRepository;

    public Set<Authority> getDefaultAuthoritiesStudent() {
        // Set default authorities
        Set<Authority> authorities = new HashSet<>();
        authorities.addAll(authorityRepository.findAllByAuthorityName("course:read"));
        authorities.addAll(authorityRepository.findAllByAuthorityName("user:read"));

        return authorities;

    }


}
