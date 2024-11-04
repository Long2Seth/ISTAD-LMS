package co.istad.lms.mapper;

import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Shift;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.portal.dto.PortalDegreeResponse;
import co.istad.lms.features.portal.dto.PortalShiftResponse;
import co.istad.lms.features.portal.dto.PortalStudyProgramResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortalMapper {

    PortalStudyProgramResponse toPortalStudyProgramResponse(StudyProgram studyProgram);

    PortalDegreeResponse toPortalDegreeResponse(Degree degree);

    PortalShiftResponse toPortalShiftResponse(Shift shift);
}
