package co.istad.lms.features.course;

import co.istad.lms.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> {

    Boolean existsByUuid(String alias);

    Optional<Course> findByUuid(String alias);

//    Optional<Course> finByOneClass(String oneClass);
 }


