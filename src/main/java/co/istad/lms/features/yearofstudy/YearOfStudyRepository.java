package co.istad.lms.features.yearofstudy;

import co.istad.lms.domain.YearOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YearOfStudyRepository extends JpaRepository<YearOfStudy,Long>, JpaSpecificationExecutor<YearOfStudy> {
}
