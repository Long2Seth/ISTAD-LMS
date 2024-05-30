package co.istad.lms.features.score;

import co.istad.lms.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScorerRepository extends JpaRepository<Score,Long>, JpaSpecificationExecutor<Score> {
}
