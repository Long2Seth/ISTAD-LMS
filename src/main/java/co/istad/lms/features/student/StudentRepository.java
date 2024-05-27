package co.istad.lms.features.student;

import co.istad.lms.domain.roles.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUuid(String uuid);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.status = :status WHERE s.uuid = :uuid")
    int updateStatusByUuid(String uuid, boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.isDeleted = :status WHERE s.uuid = :uuid")
    int updateDeletedByUuid(String uuid, boolean status);

}
