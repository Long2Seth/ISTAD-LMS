package co.istad.lms.features.instructor;

import co.istad.lms.domain.roles.Instructor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    Optional<Instructor> findByUuid(String uuid);


}
