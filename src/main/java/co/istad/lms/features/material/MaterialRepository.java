package co.istad.lms.features.material;

import co.istad.lms.domain.Material;
import co.istad.lms.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

public interface MaterialRepository extends JpaRepository<Material, Long>, JpaSpecificationExecutor<Material>{


    Set<Material> findAllBySubjectAndFileType(Subject subject,String fileType);

    Page<Material> findAllByFileType(String fileTypeString, Pageable pageable);

    boolean existsByUuid(String uuid);

    Optional<Material> findByUuid(String uuid);

}