package co.istad.lms.features.studyprogram;

import co.istad.lms.domain.StudyProgram;
import co.istad.lms.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface StudyProgramRepository extends JpaRepository<StudyProgram,Long>, JpaSpecificationExecutor<StudyProgram> {
    Optional<StudyProgram> findByAlias(String alias);

    Boolean existsByAlias(String alias);

    Optional<StudyProgram> findAllByAlias(String alias);

}
