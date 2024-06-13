package co.istad.lms.features.attendance;

import co.istad.lms.domain.Attendance;
import co.istad.lms.domain.Lecture;
import co.istad.lms.domain.roles.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

    Optional<Attendance> findByUuid(String uuid);

    boolean existsByStudentAndLecture(Student student, Lecture lecture);

    Boolean existsByUuid(String uuid);





}
