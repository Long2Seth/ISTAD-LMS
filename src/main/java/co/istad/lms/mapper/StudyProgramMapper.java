package co.istad.lms.mapper;

import co.istad.lms.domain.Shift;
import co.istad.lms.domain.StudyProgram;
import co.istad.lms.features.shift.dto.ShiftDetailResponse;
import co.istad.lms.features.shift.dto.ShiftRequest;
import co.istad.lms.features.shift.dto.ShiftResponse;
import co.istad.lms.features.shift.dto.ShiftUpdateRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramDetailResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramRequest;
import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.studyprogram.dto.StudyProgramUpdateRequest;
import co.istad.lms.util.MediaUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudyProgramMapper {

    @Mapping(target = "degree",ignore = true)
    @Mapping(target = "faculty",ignore = true)
//    @Mapping(target = "subjects",ignore = true)
    StudyProgram fromStudyProgramRequest(StudyProgramRequest studyProgramRequest);

    @Mapping(target = "logo",source = "logo",qualifiedByName = "getStudyProgramLogoUrl")
    StudyProgramDetailResponse toStudyProgramDetailResponse(StudyProgram studyProgram);

    StudyProgramResponse toStudyProgramResponse(StudyProgram studyProgram);


    @Mapping(target = "degree", ignore = true)
    @Mapping(target = "faculty", ignore = true)
//    @Mapping(target = "subjects",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudyProgramFromRequest(@MappingTarget StudyProgram studyProgram, StudyProgramUpdateRequest studyProgramUpdateRequest);

    @Named("getStudyProgramLogoUrl")
    default String getLogoUrl(String logo) {

        if (logo != null && !logo.trim().isEmpty()) {
            return MediaUtil.getUrl(logo);
        } else {
            return null;
        }
    }

}