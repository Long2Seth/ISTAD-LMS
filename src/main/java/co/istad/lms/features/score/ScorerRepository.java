package co.istad.lms.features.score;

import co.istad.lms.domain.Score;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface ScorerRepository extends JpaRepository<Score,Long>, JpaSpecificationExecutor<Score> {

    Optional<Score> findByUuid(String uuid);
}
