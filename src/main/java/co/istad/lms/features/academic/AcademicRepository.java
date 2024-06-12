package co.istad.lms.features.academic;

import co.istad.lms.domain.User;
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

    Optional<Academic> findByUser(User user);


}
