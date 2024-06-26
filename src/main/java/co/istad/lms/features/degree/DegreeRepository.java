package co.istad.lms.features.degree;

import co.istad.lms.domain.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
    Optional<Degree> findByAlias(String name);

    Boolean existsByAlias(String alias);

    Boolean existsByLevel(String level);
}
