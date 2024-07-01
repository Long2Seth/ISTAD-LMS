package co.istad.lms.features.subject;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Subject;
import co.istad.lms.domain.YearOfStudy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {

    Optional<Subject> findByAlias(String alias);

    Optional<Subject> findByTitle(String title);

    Boolean existsByAlias(String alias);

    Optional<Subject> findAllByAlias(String alias);

    Optional<Subject> findByAliasAndIsDeletedFalseAndIsDraftFalse(String alias);

    Optional<Subject> findByAliasAndIsDeletedFalse(String alias);

    @Query("SELECT s FROM Subject s JOIN s.yearOfStudies y WHERE y.uuid IN :yearOfStudyUuids")
    Page<Subject> findByYearOfStudyUuids(@Param("yearOfStudyUuids") Set<String> yearOfStudyUuids, Pageable pageable);
}

