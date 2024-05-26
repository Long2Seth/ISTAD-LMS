package co.istad.lms.features.course;

import co.istad.lms.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> {
}
