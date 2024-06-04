package co.istad.lms.features.academic;

import co.istad.lms.features.academic.dto.AcademicRequest;
import co.istad.lms.features.academic.dto.AcademicRequestDetail;
import co.istad.lms.features.academic.dto.AcademicResponse;
import co.istad.lms.features.academic.dto.AcademicResponseDetail;
import org.springframework.data.domain.Page;

public interface AcademicService {

    AcademicResponse createAcademic(AcademicRequest academicRequest);

    AcademicResponseDetail updateAcademicByUuid(String uuid , AcademicRequestDetail academicRequestDetail);

    AcademicResponseDetail getAcademicDetailByUuid(String uuid);

    AcademicResponse getAcademicsByUuid(String uuid);

    void deleteAcademicByUuid(String uuid);

    AcademicResponseDetail updateDisableAcademicByUuid(String uuid);

    AcademicResponseDetail updateEnableAcademicByUuid(String uuid);

    AcademicResponseDetail updateDeletedAcademicByUuid(String uuid);

    Page<AcademicResponseDetail> getAcademics(int page, int limit);

    Page<AcademicResponse> getAcademicsDetail(int page, int limit);
}
