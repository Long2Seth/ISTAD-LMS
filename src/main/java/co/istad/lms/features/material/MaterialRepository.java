package co.istad.lms.features.material;

import co.istad.lms.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long>, JpaSpecificationExecutor<Material>{

    Optional<Material> findByAlias(String alias);

    Boolean existsByAlias(String alias);

}