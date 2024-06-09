package co.istad.lms.features.graduation;


import co.istad.lms.domain.Graduation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GraduationRepository extends JpaRepository<Graduation, Long> {

    Optional<Graduation> findByUuid(String uuid);

    Optional<Graduation> findByGpa(String gpa);

    boolean existsByGpa(String gpa);
}
