package co.istad.lms.mapper;

import co.istad.lms.domain.Course;
import co.istad.lms.domain.Degree;
import co.istad.lms.features.course.dto.CourseDetailResponse;
import co.istad.lms.features.course.dto.CourseRequest;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.course.dto.CourseUpdateRequest;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "subject",ignore = true)
    @Mapping(target = "instructor",ignore = true)
    @Mapping(target = "oneClass",ignore = true)
    Course fromCourseRequest(CourseRequest courseRequest);

    CourseDetailResponse toCourseDetailResponse(Course course);

    CourseResponse toCourseResponse(Course course);

    @Mapping(target = "subject",ignore = true)
    @Mapping(target = "instructor",ignore = true)
    @Mapping(target = "oneClass",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCourseFromRequest(@MappingTarget Course course, CourseUpdateRequest courseUpdateRequest);

}
