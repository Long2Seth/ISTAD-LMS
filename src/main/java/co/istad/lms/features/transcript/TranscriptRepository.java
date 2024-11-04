package co.istad.lms.features.transcript;

import co.istad.lms.domain.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TranscriptRepository extends JpaRepository<Transcript,Long>, JpaSpecificationExecutor<Transcript> {
}
