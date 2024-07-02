package co.istad.lms.features.summarydashboard;

import co.istad.lms.features.material.dto.MaterialDetailResponse;
import co.istad.lms.features.summarydashboard.dto.SummaryDashboardDetailResponse;
import co.istad.lms.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/summary-dashboards")
public class SummaryDashboardController {

    private final SummaryDashboardService summaryDashboardService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('faculty:read')")
    public SummaryDashboardDetailResponse getSummaryDashboard() {

        return summaryDashboardService.getSummaryDashboard();
    }
}
