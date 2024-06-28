package co.istad.lms.mapper;

import co.istad.lms.domain.Lecture;
import co.istad.lms.features.lecture.dto.*;
import co.istad.lms.util.DateTimeUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.mapstruct.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, InstructorMapper.class})
public interface LectureMapper {

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "lectureDate",ignore = true)
    Lecture fromLectureRequest(LectureRequest lectureCreateRequest);

    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "localTimeToString")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "localTimeToString")
    LectureDetailResponse toLectureDetailResponse(Lecture lecture,String classCode,String startTime,String endTime);

    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "localTimeToString")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "localTimeToString")
    @Mapping(target = "courseTitle", source = "lecture.course.title")
    LectureInstructorScheduleResponse toLectureInstructorScheduleResponse(Lecture lecture, String classCode, String startTime, String endTime);


    @Named("localTimeToString")
    static String localTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }
    LectureResponse toLectureResponse(Lecture lecture);

    @Mapping(target = "startTime",ignore = true)
    @Mapping(target = "endTime",ignore = true)
    @Mapping(target = "lectureDate",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLectureFromRequest(@MappingTarget Lecture lecture, LectureUpdateRequest lectureUpdateRequest);



}
