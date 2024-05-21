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
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudyProgramMapper {

    StudyProgram fromStudyProgramRequest(StudyProgramRequest studyProgramRequest);

    StudyProgramDetailResponse toStudyProgramDetailResponse(StudyProgram studyProgram);

    StudyProgramResponse toStudyProgramResponse(StudyProgram studyProgram);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudyProgramFromRequest(@MappingTarget StudyProgram studyProgram, StudyProgramUpdateRequest studyProgramUpdateRequest);
}