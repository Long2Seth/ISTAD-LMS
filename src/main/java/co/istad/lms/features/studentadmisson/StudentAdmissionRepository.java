package co.istad.lms.features.studentadmisson;

import co.istad.lms.domain.StudentAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StudentAdmissionRepository extends JpaRepository<StudentAdmission,Long>, JpaSpecificationExecutor<StudentAdmission> {

    Optional<StudentAdmission> findByUuid(String uuid);
}
