package co.istad.lms.features.lecture;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long>, JpaSpecificationExecutor<Lecture>{

    Optional<Lecture> findByAlias(String alias);

    Boolean existsByAlias(String alias);


}
