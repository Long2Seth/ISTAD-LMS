package co.istad.lms.features.academic;

import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestDetail;
import co.istad.lms.features.academic.dto.AcademicResponseDetail;
import org.springframework.data.domain.Page;

public interface AcademicService {

    AcademicResponseDetail createAcademic(AcademicRequest academicRequest);

    AcademicResponseDetail updateAcademicByUuid(String uuid , AcademicRequestDetail academicRequestDetail);

    AcademicResponseDetail getAcademicByUuid(String uuid);

    AcademicResponseDetail deleteAcademicByUuid(String uuid);

    AcademicResponseDetail updateDisableAcademicByUuid(String uuid);

    AcademicResponseDetail updateEnableAcademicByUuid(String uuid);

    AcademicResponseDetail updateDeletedAcademicByUuid(String uuid);

    Page<AcademicResponseDetail> getAcademics(int page, int limit);
}
