package co.istad.lms.mapper;

import co.istad.lms.domain.Lecture;
import co.istad.lms.features.lecture.dto.LectureDetailResponse;
import co.istad.lms.features.lecture.dto.LectureRequest;
import co.istad.lms.features.lecture.dto.LectureResponse;
import co.istad.lms.features.lecture.dto.LectureUpdateRequest;
import co.istad.lms.util.DateTimeUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.mapstruct.*;

import java.time.LocalTime;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, InstructorMapper.class})
public interface LectureMapper {

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "lectureDate",ignore = true)
    Lecture fromLectureRequest(LectureRequest lectureCreateRequest);

    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "startTime",ignore = true)
    LectureDetailResponse toLectureDetailResponse(Lecture lecture,String classCode,String startTime,String endTime);

    LectureResponse toLectureResponse(Lecture lecture);

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "lectureDate",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLectureFromRequest(@MappingTarget Lecture lecture, LectureUpdateRequest lectureUpdateRequest);


}
