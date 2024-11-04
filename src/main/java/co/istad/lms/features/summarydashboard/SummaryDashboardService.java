package co.istad.lms.features.summarydashboard;

import co.istad.lms.features.summarydashboard.dto.SummaryDashboardDetailResponse;
import co.istad.lms.security.CustomUserDetails;

public interface SummaryDashboardService {

    SummaryDashboardDetailResponse getSummaryDashboard();
}
