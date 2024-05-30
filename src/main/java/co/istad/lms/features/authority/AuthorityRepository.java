package co.istad.lms.features.authority;


import co.istad.lms.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityName(String authorityName);
    Set<Authority> findAllByAuthorityName(String authorityName);

    Optional<Authority> findByUuid(String uuid);
    boolean existsByAuthorityName(String authorityName);
}
