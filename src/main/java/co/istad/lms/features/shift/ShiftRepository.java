package co.istad.lms.features.shift;

import co.istad.lms.domain.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift,Long> {
}
