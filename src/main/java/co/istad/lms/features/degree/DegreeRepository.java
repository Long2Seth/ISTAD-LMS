package co.istad.lms.features.degree;

import co.istad.lms.domain.Degree;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long>, JpaSpecificationExecutor<Degree> {
    Optional<Degree> findByAlias(String alias);

    Optional<Degree> findByLevel(String level);

    Boolean existsByAlias(String alias);

    Boolean existsByLevel(String level);
}
