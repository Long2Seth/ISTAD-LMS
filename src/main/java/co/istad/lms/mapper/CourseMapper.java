package co.istad.lms.mapper;

import co.istad.lms.domain.Course;
import co.istad.lms.domain.Degree;
import co.istad.lms.domain.Score;
import co.istad.lms.features.course.dto.*;
import co.istad.lms.features.degree.dto.DegreeDetailResponse;
import co.istad.lms.features.degree.dto.DegreeRequest;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.degree.dto.DegreeUpdateRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, InstructorMapper.class, SubjectMapper.class, ClassMapper.class , ScoreMapper.class})
public interface CourseMapper {

    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "oneClass", ignore = true)
    @Mapping(target = "courseStart", ignore = true)
    Course fromCourseRequest(CourseRequest courseRequest);

    CourseDetailResponse toCourseDetailResponse(Course course);

    CourseSemesterScoreResponse toCourseSemesterScoreResponse(Course course);

    CourseResponse toCourseResponse(Course course);

    CourseClassResponse toCourseClassResponse(Course course);

    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "oneClass", ignore = true)
    @Mapping(target = "courseStart", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCourseFromRequest(@MappingTarget Course course, CourseUpdateRequest courseUpdateRequest);


    @Named("toCourseStudentResponse")
    @Mapping(source = "instructor.user.profileImage", target = "instructorProfileImage")
    @Mapping(source = "instructor.user.nameEn", target = "instructorName")
    @Mapping(source = "yearOfStudy.year", target = "year")
    @Mapping(source = "yearOfStudy.semester", target = "semester")
    @Mapping(source = "subject.credit", target = "credit")
    @Mapping(source = "subject.logo", target = "logo")
    @Mapping(source = "subject.description", target = "description")
    CourseWithUsersResponse toCourseStudentResponse(Course course);


    @Named("toUseCourseResponse")
    @Mapping(source = "subject.title", target = "title")
    @Mapping(source = "subject.credit", target = "credit")
    @Mapping(source = "scores", target = "score", qualifiedByName = "mapScoresToTotal")
    @Mapping(source = "scores", target = "grade", qualifiedByName = "mapScoresToAverageGrade")
    CourseResponse toUseCourseResponse(Course course);

    @Named("mapScoresToTotal")
    default Double mapScoresToTotal(Set<Score> scores) {
        return scores.stream()
                .mapToDouble(Score::getTotal)
                .sum();
    }

    @Named("mapScoresToAverageGrade")
    default String mapScoresToAverageGrade(Set<Score> scores) {
        return scores.stream()
                .map(Score::getGrade)
                .collect(Collectors.joining(", "));
    }


}
