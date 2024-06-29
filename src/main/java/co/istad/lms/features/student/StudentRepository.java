package co.istad.lms.features.student;

import co.istad.lms.domain.User;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {


    @Query("SELECT s FROM Student s WHERE s.cardId = (SELECT MAX(s2.cardId) FROM Student s2 WHERE s2.cardId LIKE 'G-%')")
    Optional<Student> findStudentWithMaxCardId();

    Optional<Student> findByUuid(String uuid);

    Optional<Student> findStudentByUserUuid(String userUuid);

    Page<Student> findAllByCoursesYearOfStudy(YearOfStudy yearOfStudy,Pageable pageable);

    Optional<Student> findByUserUsername(String username);

    Optional<Student> findByUser(User user);

    Page<Student> findStudentByClassesUuid(String uuid, Pageable pageable);



}
