package co.istad.lms.features.classes;

import co.istad.lms.domain.Class;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.YearOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.Set;

public interface ClassRepository extends JpaRepository<Class,Long>, JpaSpecificationExecutor<Class> {

    Optional<Class> findByAlias(String alias);
    Optional<Class> findByStudyProgramAndYearOfStudies(StudyProgram studyProgram, Set<YearOfStudy> yearOfStudies);

    Boolean existsByAlias(String alias);
}
