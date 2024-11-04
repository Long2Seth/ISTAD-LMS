package co.istad.lms.features.generation;

import co.istad.lms.domain.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GenerationRepository extends JpaRepository<Generation,Long>, JpaSpecificationExecutor<Generation> {

    Boolean existsByAlias(String alias);

    Optional<Generation> findByAlias(String alais);
}
