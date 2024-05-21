package co.istad.lms.features.authority;


import co.istad.lms.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityName(String authorityName);
    boolean existsByAuthorityName(String authorityName);
}
