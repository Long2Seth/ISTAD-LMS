package co.istad.lms.features.course;

import co.istad.lms.domain.Course;
import co.istad.lms.domain.Generation;
import co.istad.lms.domain.YearOfStudy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> {

    Boolean existsByUuid(String uuid);

    Optional<Course> findByUuid(String uuid);

    Optional<Course> findByUuidAndIsDeletedFalse(String uuid);

//    Optional<Course> findByStudentUserUuidAndYearOfStudyUuid(String studentUuid, String yearOfStudyUuid);

    Page<Course> findByOneClassGenerationAndYearOfStudy(Generation generation, YearOfStudy yearOfStudy, Pageable pageable);


//    Optional<Course> finByOneClass(String oneClass);
 }


