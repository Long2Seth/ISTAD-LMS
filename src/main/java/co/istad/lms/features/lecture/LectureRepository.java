package co.istad.lms.features.lecture;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LectureRepository extends JpaRepository<Lecture, Long>, JpaSpecificationExecutor<Lecture>{

    Optional<Lecture> findByUuid(String alias);

    Boolean existsByUuid(String alias);

    Page<Lecture> findAllByCourseInstructorUserUuidAndStatusInAndIsDraftAndIsDeleted(String uuid, List<Integer> status
            , boolean isDraft, boolean isDeleted, Pageable pageable);

    Set<Lecture> findAllByStatus(Integer status);


}
