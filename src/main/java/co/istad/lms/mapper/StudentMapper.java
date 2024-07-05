package co.istad.lms.mapper;

import co.istad.lms.domain.Course;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.course.dto.CourseWithUsersResponse;
import co.istad.lms.features.student.dto.*;
import co.istad.lms.features.user.dto.UserProfile;
import co.istad.lms.util.MediaUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class, ClassMapper.class})
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
                                                                Set<CourseSemesterScoreResponse> courses, String grade
            , Double total, Double gpa, String classCode);

    @Named("mapCourses")
    static Set<CourseSemesterScoreResponse> mapCourses(Set<CourseSemesterScoreResponse> courses) {

        return courses;
    }

    StudentCourseResponse toResponseCourse(Student student);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    @Mapping(source = "courses", target = "courses", qualifiedByName = "toUseCourseResponse")
    @Mapping(target = "profileImage", source = "user.profileImage")
    StudentResponseDetail toResponseDetail(Student student);

    @Mapping(source = "student.user.nameEn", target = "nameEn")
    @Mapping(source = "student.user.uuid", target = "uuid")
    @Mapping(source = "student.user.gender", target = "gender")
    StudentScoreResponse toStudentScoreResponse(Student student);

    @Mapping(source = "student.user.nameEn", target = "nameEn")
    @Mapping(source = "student.user.uuid", target = "uuid")
    @Mapping(source = "student.user.gender", target = "gender")
    @Mapping(source = "student.studentStatus", target = "status")
    @Mapping(source = "student.user.dob", target = "dob")
    StudentTranscriptResponse toStudentTranscriptResponse(Student student, Integer year, Double semester1Score,
                                                          Double semester2Score, String grade, Double gpa, Double average);

//    (student,scoreTranscriptRequest.year(), averageSemester1,
//    averageSemester2,grade,gpa,average);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    void updateStudentFromRequest(@MappingTarget Student student, StudentRequestUpdate studentRequestUpdate);


    @Mapping(target = "gender", source="user.gender")
    @Mapping(target = "profileImage", source="user.profileImage" , qualifiedByName = "getUrl")
    @Mapping(target = "phoneNumber", source="user.phoneNumber")
    @Mapping(target = "currentAddress", source="user.currentAddress")
    @Mapping(target = "birthPlace" , source = "user.birthPlace" )
    StudentSetting toStudentSettingResponse(Student student);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentSettingRequest(@MappingTarget Student student, StudentSetting studentSettingRequest);


    default Set<CourseWithUsersResponse> toCourseStudentResponses(Set<Course> courses, @Context CourseMapper courseMapper) {
        return courses.stream()
                .map(courseMapper::toCourseStudentResponse)
                .collect(Collectors.toSet());
    }


    @Mapping(target = "year", source = "course.yearOfStudy.year")
    @Mapping(target = "semester", source = "course.yearOfStudy.semester")
    @Mapping(target = "courseTitle", source = "course.subject.title")
    @Mapping(target = "courseDescription", source = "course.subject.description")
    @Mapping(target = "courseLogo", source = "course.subject.logo" , qualifiedByName = "getUrl")
    @Mapping(target = "credit", source = "course.subject.credit")
    @Mapping(target = "theory", source = "course.subject.theory")
    @Mapping(target = "practice", source = "course.subject.practice")
    @Mapping(target = "internship", source = "course.subject.internship")
    @Mapping(target = "instructorName", source = "course.instructor.user.nameEn")
    @Mapping(target = "userProfileImage", source = "course.instructor.user.profileImage" , qualifiedByName = "getUrl")
    @Mapping(target = "position", source = "course.instructor.user.position")
    @Mapping(target = "classesStart", source = "course.oneClass.classStart")
    @Mapping(source = "course.students", target = "studentProfileImage", qualifiedByName = "getProfileImage")
    StudentCourseDetailResponse toStudentCourseDetailResponse(Student student, Course course);


    @Named("getUrl")
    default String getUrl(String logo) {

        if (logo != null && !logo.trim().isEmpty()) {
            return MediaUtil.getUrl(logo);
        } else {
            return null;
        }
    }


    @Named("getProfileImage")
    default String getProfileImage(Student student) {
        return (student.getUser().getProfileImage() != null && !student.getUser().getProfileImage().trim().isEmpty()) ?
                MediaUtil.getUrl(student.getUser().getProfileImage()) : null;
    }


}
