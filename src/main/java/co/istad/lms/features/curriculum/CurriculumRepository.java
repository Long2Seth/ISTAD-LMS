package co.istad.lms.features.curriculum;

import co.istad.lms.domain.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    boolean existsBySemesterAndYear(String semester, String year);

    Optional<Curriculum> findBySemesterAndYear(String semester , String year);


}
