package co.istad.lms.features.degree;

import co.istad.lms.domain.Degree;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
    Optional<Degree> findByAlias(String name);

    Optional<Degree> findByLevel(String level);

    Boolean existsByAlias(String alias);

    Boolean existsByLevel(String level);
}
