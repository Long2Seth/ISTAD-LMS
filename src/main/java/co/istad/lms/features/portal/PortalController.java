package co.istad.lms.features.portal;

import co.istad.lms.features.studyprogram.StudyProgramService;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portals")
public class PortalController{

    private final StudyProgramService studyProgramService;
    @GetMapping("/study-programs")
    public Page<StudyProgramDetailResponse> getAllStudyPrograms(

            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {

        return studyProgramService.getAllStudyPrograms(pageNumber, pageSize);

    }
}
