package co.istad.lms.features.shift;

import co.istad.lms.domain.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift,Long>, JpaSpecificationExecutor<Shift> {

    Optional<Shift> findByAlias(String alias);


    Boolean existsByAlias(String alias);
}
