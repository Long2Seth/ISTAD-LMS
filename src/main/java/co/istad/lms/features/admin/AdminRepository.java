package co.istad.lms.features.admin;

import co.istad.lms.domain.roles.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

    Optional<Admin> findByUuid(String uuid);

    @Modifying
    @Transactional
    @Query("UPDATE Admin as a SET a.status = :status WHERE a.uuid = :uuid")
    int updateStatusByUuid(String uuid, boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE Admin as a SET a.isDeleted = :status WHERE a.uuid = :uuid")
    int updateDeletedStatusByUuid(String uuid, boolean status);


}
