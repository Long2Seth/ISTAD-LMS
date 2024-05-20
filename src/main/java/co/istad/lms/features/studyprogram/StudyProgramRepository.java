package co.istad.lms.features.studyprogram;

import co.istad.lms.domain.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyProgramRepository extends JpaRepository<StudyProgram,Long> {
    Optional<StudyProgram> findByAlias(String alias);
}
