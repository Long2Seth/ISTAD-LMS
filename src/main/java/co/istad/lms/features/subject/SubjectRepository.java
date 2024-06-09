package co.istad.lms.features.subject;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {

    Optional<Subject> findByAlias(String alias);

    Optional<Subject> findByTitle(String title);

    Boolean existsByAlias(String alias);

    Optional<Subject> findAllByAlias(String alias);

    Optional<Subject> findByAliasAndIsDeletedFalseAndIsDraftFalse(String alias);

    Optional<Subject> findByAliasAndIsDeletedFalse(String alias);


}

