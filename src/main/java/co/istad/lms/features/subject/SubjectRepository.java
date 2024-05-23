package co.istad.lms.features.subject;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {

    Optional<Subject> findByAlias(String alias);

    Optional<Subject> findBySubjectName(String level);

    Boolean existsByAlias(String alias);


}

