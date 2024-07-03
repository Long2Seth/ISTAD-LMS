package co.istad.lms.features.yearofstudy;

import co.istad.lms.domain.*;
import co.istad.lms.domain.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Struct;
import java.util.Optional;
import java.util.Set;

public interface YearOfStudyRepository extends JpaRepository<YearOfStudy,Long>, JpaSpecificationExecutor<YearOfStudy> {

    Optional<YearOfStudy> findByUuid(String uuid);

    Optional<YearOfStudy> findByYearAndSemesterAndStudyProgram(Integer year, Integer semester, StudyProgram studyProgram);

    Set<YearOfStudy> findByYearAndStudyProgram(Integer year, StudyProgram studyProgram);

    Set<YearOfStudy> findByCourses(Course course);


    Set<YearOfStudy> findYearOfStudiesByStudyProgram(StudyProgram studyProgram);

    Optional<YearOfStudy> findBySubjectsAndStudyProgram(Subject subject,StudyProgram studyProgram);

    Set<YearOfStudy> findAllByYearAndStudyProgramAlias(Integer year,String studyProgramAlias);
}
