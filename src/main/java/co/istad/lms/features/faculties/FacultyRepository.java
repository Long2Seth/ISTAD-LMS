package co.istad.lms.features.faculties;

import co.istad.lms.domain.Faculty;
import co.istad.lms.features.faculties.dto.FacultyDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long>, JpaSpecificationExecutor<Faculty> {
    Boolean existsByAlias(String alias);

    Optional<Faculty> findByAlias(String alias);

    Optional<Faculty> findByAliasAndIsDeletedFalseAndIsDraftFalse(String alias);

    Optional<Faculty> findByAliasAndIsDeletedFalse(String alias);
}
