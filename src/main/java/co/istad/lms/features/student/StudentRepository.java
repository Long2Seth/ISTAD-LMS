package co.istad.lms.features.student;

import co.istad.lms.domain.roles.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>, JpaSpecificationExecutor<Student> {

    Optional<Student> findByUuid(String uuid);

    Boolean existsByUuid(String uuid);
}
