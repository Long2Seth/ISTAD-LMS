package co.istad.lms.features.classes;

import co.istad.lms.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class,Long>, JpaSpecificationExecutor<Class> {

    Optional<Class> findByAlias(String alias);
}
