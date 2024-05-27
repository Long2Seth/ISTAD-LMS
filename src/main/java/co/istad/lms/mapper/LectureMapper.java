package co.istad.lms.mapper;

import co.istad.lms.domain.Lecture;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LectureMapper {

    Lecture fromLectureRequest(LectureRequest lectureCreateRequest);

    LectureDetailResponse toLectureDetailResponse(Lecture lecture);

    LectureResponse toLectureResponse(Lecture lecture);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLectureFromRequest(@MappingTarget Lecture lecture, LectureUpdateRequest lectureUpdateRequest);

}
