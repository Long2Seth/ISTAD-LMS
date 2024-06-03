package co.istad.lms.features.academicyear;

import co.istad.lms.domain.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AcademicYearRepository extends JpaRepository<AcademicYear,Long>, JpaSpecificationExecutor<AcademicYear> {
}
