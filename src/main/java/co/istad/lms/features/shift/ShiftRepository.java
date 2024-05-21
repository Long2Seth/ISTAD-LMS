package co.istad.lms.features.shift;

import co.istad.lms.domain.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift,Long> {

    Optional<Shift> findByAlias(String alias);


    Boolean existsByAlias(String alias);
}
