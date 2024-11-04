package co.istad.lms.features.portal;

import co.istad.lms.features.portal.dto.PortalDegreeResponse;
import co.istad.lms.features.portal.dto.PortalShiftResponse;
import co.istad.lms.features.portal.dto.PortalStudyProgramResponse;
import co.istad.lms.features.studyprogram.StudyProgramService;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portals")
public class PortalController{

    private final PortalService portalService;
    @GetMapping("/study-programs")
    public Set<PortalStudyProgramResponse> getAllStudyPrograms() {

        return portalService.getAllStudyPrograms();

    }

    @GetMapping("/degrees")
    public Set<PortalDegreeResponse> getAllDegrees() {

        return portalService.getAllDegrees();

    }

    @GetMapping("/shifts")
    public Set<PortalShiftResponse> getAllShifts() {

        return portalService.getAllShifts();

    }
}
