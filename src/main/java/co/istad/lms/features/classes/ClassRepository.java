package co.istad.lms.features.classes;

import co.istad.lms.domain.Class;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClassRepository extends JpaRepository<Class,Long>, JpaSpecificationExecutor<Class> {

    Optional<Class> findByUuid(String uuid);

    Optional<Class> findByUuidAndIsDeletedFalse(String uuid);

    Optional<Class> findByStudyProgramAndYearOfStudies(StudyProgram studyProgram, Set<YearOfStudy> yearOfStudies);

    Boolean existsByUuid(String uuid);

    Set<Class> findByStudents(Student student);
}
