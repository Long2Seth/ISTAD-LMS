package co.istad.lms.features.academic;

import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicResponse;
import org.springframework.data.domain.Page;

public interface AcademicService {

    AcademicResponse createAcademic(AcademicRequest academicRequest);

    AcademicResponse updateAcademicByUuid(String uuid , AcademicRequest academicRequest);

    AcademicResponse getAcademicByUuid(String uuid);

    AcademicResponse deleteAcademicByUuid(String uuid);

    AcademicResponse updateDisableAcademicByUuid(String uuid);

    AcademicResponse updateEnableAcademicByUuid(String uuid);

    AcademicResponse updateDeletedAcademicByUuid(String uuid);

    Page<AcademicResponse> getAcademics(int page, int limit);
}
