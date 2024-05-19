package co.istad.lms.features.studyprogram;

import co.istad.lms.domain.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyProgramRepository extends JpaRepository<StudyProgram,Long> {
}
