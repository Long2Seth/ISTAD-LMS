package co.istad.lms.features.yearofstudy;

import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.YearOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface YearOfStudyRepository extends JpaRepository<YearOfStudy,Long>, JpaSpecificationExecutor<YearOfStudy> {

    Optional<YearOfStudy> findByUuid(String uuid);

    Optional<YearOfStudy> findByYearAndSemesterAndStudyProgram(Integer year, Integer semester, StudyProgram studyProgram);

    Set<YearOfStudy> findByYearAndStudyProgram(Integer year, StudyProgram studyProgram);
}
