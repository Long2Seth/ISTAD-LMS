package co.istad.lms.features.academic;

import co.istad.lms.domain.roles.Academic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long>{

    Optional<Academic> findByUuid(String uuid);

    @Modifying
    @Transactional
    @Query("UPDATE Academic as a SET a.status = :status WHERE a.uuid = :uuid")
    int updateStatusByUuid(String uuid, boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE Academic as a SET a.isDeleted = :status WHERE a.uuid = :uuid")
    int updateDeletedStatusByUuid(String uuid, boolean status);

}
