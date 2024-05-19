package co.istad.lms.features.degree;

import co.istad.lms.domain.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree,Long> {
}
