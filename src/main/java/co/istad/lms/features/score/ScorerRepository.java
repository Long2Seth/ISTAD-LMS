package co.istad.lms.features.score;

import co.istad.lms.domain.Class;
import co.istad.lms.domain.Course;
import co.istad.lms.domain.Score;
import co.istad.lms.domain.roles.Student;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;
import java.util.Set;

public interface ScorerRepository extends JpaRepository<Score,Long>, JpaSpecificationExecutor<Score> {

    Optional<Score> findByUuid(String uuid);

    Optional<Score> findByCourseAndStudent(Course course,Student student);

//    Set<Score> findAllByStudentUuidAndCourse_CourseYearOfStudy_Semester();

    boolean existsByStudentAndCourse(Student student, Course course);

    Page<Score> findAllByCourseUuid(String uuid,Pageable pageable);

    Page<Score> findAllByCourseInstructorUserUuid(String uuid,Pageable pageable);
}
