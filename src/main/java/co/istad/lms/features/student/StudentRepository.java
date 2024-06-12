package co.istad.lms.features.student;

import co.istad.lms.domain.User;
import co.istad.lms.domain.roles.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUuid(String uuid);

    Optional<Student> findByUserUsername(String username);

    Optional<Student> findByUser(User user);



}
