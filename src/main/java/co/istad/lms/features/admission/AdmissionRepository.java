package co.istad.lms.features.admission;

import co.istad.lms.domain.Admission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    Optional<Admission> findByUuid(String uuid);

    Boolean existsByNameEn(String nameEn);

    Boolean existsByNameKh(String nameKh);

    List<Admission> findByNameEnIgnoreCase(String nameEn);

    List<Admission> findByNameKhIgnoreCase(String nameKh);

    Page<Admission> findByNameEnContainingIgnoreCase(String nameEn, Pageable pageable);

    Page<Admission> findByNameKhContainingIgnoreCase(String nameKh, Pageable pageable);
}
