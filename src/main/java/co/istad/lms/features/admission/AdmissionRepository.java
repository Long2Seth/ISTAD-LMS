package co.istad.lms.features.admission;

import co.istad.lms.domain.Admission;
import co.istad.lms.domain.StudentAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdmissionRepository extends JpaRepository<Admission, Long>,JpaSpecificationExecutor<Admission> {
    Optional<Admission> findByUuid(String uuid);

    Optional<Admission> findByStatus(Integer status);

    Set<Admission> findAllByStatus(Integer status);
}
