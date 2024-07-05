package co.istad.lms.features.portal;

import co.istad.lms.features.portal.dto.PortalDegreeResponse;
import co.istad.lms.features.portal.dto.PortalShiftResponse;
import co.istad.lms.features.portal.dto.PortalStudyProgramResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;

import java.util.Set;

public interface PortalService {

    Set<PortalStudyProgramResponse> getAllStudyPrograms();
    Set<PortalDegreeResponse> getAllDegrees();

    Set<PortalShiftResponse> getAllShifts();
}
