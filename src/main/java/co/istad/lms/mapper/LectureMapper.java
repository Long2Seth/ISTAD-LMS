package co.istad.lms.mapper;

import co.istad.lms.domain.Lecture;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LectureMapper {

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "lectureDate",ignore = true)
    Lecture fromLectureRequest(LectureRequest lectureCreateRequest);


    LectureDetailResponse toLectureDetailResponse(Lecture lecture);

    LectureResponse toLectureResponse(Lecture lecture);

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "lectureDate",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLectureFromRequest(@MappingTarget Lecture lecture, LectureUpdateRequest lectureUpdateRequest);

}
