package co.istad.lms.features.portal.dto;

import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;

public record PortalStudyProgramResponse(
        String alias,
        String studyProgramName,
        String description,
        String logo
) {
}
