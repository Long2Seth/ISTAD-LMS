package co.istad.lms.mapper;

import co.istad.lms.domain.Course;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.course.dto.CourseStudentResponse;
import co.istad.lms.features.student.dto.*;
import co.istad.lms.features.user.dto.UserProfile;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class})
public interface StudentMapper {

    @Mapping(target = "user.dob", ignore = true)
    Student toRequest(StudentRequest studentRequest);


    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    StudentResponse toResponse(Student student);

    @Mapping(source = "student.studentStatus", target = "status")
    @Mapping(source = "student.user.uuid", target = "uuid")
    @Mapping(source = "student.user.nameEn", target = "nameEn")
    @Mapping(source = "student.user.dob", target = "dob")
    @Mapping(source = "student.user.gender", target = "gender")
    @Mapping(source = "courses", target = "courses", qualifiedByName = "mapCourses")
    StudentSemesterScoreResponse toStudentSemesterScoreResponse(Student student,
                                                                Set<CourseSemesterScoreResponse> courses,String grade
            , Double total);

    @Named("mapCourses")
    static Set<CourseSemesterScoreResponse> mapCourses(Set<CourseSemesterScoreResponse> courses) {

        return courses;
    }

    StudentCourseResponse toResponseCourse(Student student);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    StudentResponseDetail toResponseDetail(Student student);

    @Mapping(source = "student.user.nameEn",target = "nameEn")
    @Mapping(source = "student.user.uuid",target = "uuid")
    @Mapping(source = "student.user.gender",target = "gender")
    StudentScoreResponse toStudentScoreResponse(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    void updateStudentFromRequest(@MappingTarget Student student, StudentRequestUpdate studentRequestUpdate);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentSettingRequest(@MappingTarget Student student, StudentSettingRequest studentSettingRequest);



    default Set<CourseStudentResponse> toCourseStudentResponses(Set<Course> courses, @Context CourseMapper courseMapper) {
        return courses.stream()
                .map(courseMapper::toCourseStudentResponse)
                .collect(Collectors.toSet());
    }


    @Mapping(target = "year", source = "yearOfStudy.year")
    @Mapping(target = "semester", source = "yearOfStudy.semester")
    @Mapping(target = "courseTitle", source = "course.title")
    @Mapping(target = "courseDescription", source = "course.subject.description")
    @Mapping(target = "courseLogo", source = "course.subject.logo")
    @Mapping(target = "credit", source = "course.subject.credit")
    @Mapping(target = "theory", source = "course.subject.theory")
    @Mapping(target = "practice", source = "course.subject.practice")
    @Mapping(target = "internship", source = "course.subject.internship")
    @Mapping(target = "instructorName", source = "course.instructor.user.nameEn")
    @Mapping(target = "position", source = "course.instructor.user.position")
    StudentCourseDetailResponse toStudentCourseDetailResponse(Course course, YearOfStudy yearOfStudy);



}
