package co.istad.lms.mapper;

import co.istad.lms.domain.Course;
import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.domain.roles.Instructor;
import co.istad.lms.domain.roles.Student;
import co.istad.lms.features.course.dto.CourseWithUsersResponse;
import co.istad.lms.features.instructor.dto.*;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring",uses = {UserMapper.class, CourseMapper.class , ClassMapper.class})
public interface InstructorMapper {





    @Mapping(target = "user.dob" , ignore = true)
    Instructor toRequest(InstructorRequest request);



    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    InstructorResponse toResponse(Instructor instructor);


    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponse")
    InstructorInfoResponse toCourseResponse(Instructor instructor);

    @Mapping(source = "user", target = ".", qualifiedByName = "toUserResponseDetail")
    InstructorResponseDetail toResponseDetail(Instructor instructor);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInstructorFromRequest(@MappingTarget Instructor instructor, InstructorRequestUpdate instructorRequestUpdate);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
void updateInstructorFromSettingRequest(@MappingTarget Instructor instructor, InstructorSettingRequest instructorSettingRequest);


    @Mapping( source = "instructor.user.position" , target = "position")
    @Mapping( source = "instructor.user.profileImage" , target = "userProfileImage")
    @Mapping( source = "instructor.user.nameEn" , target = "instructorName")
    @Mapping( source = "course.subject.title" , target = "courseTitle")
    @Mapping( source = "course.subject.description" , target = "courseDescription")
    @Mapping( source = "course.subject.logo" , target = "courseLogo")
    @Mapping( source = "course.subject.credit" , target = "credit")
    @Mapping( source = "course.subject.theory" , target = "theory")
    @Mapping( source = "course.subject.practice" , target = "practice")
    @Mapping( source = "course.subject.internship" , target = "internship")
    @Mapping( source = "course.oneClass.classStart" , target = "classesStart")
    @Mapping( source = "course.yearOfStudy.year" , target = "year")
    @Mapping( source = "course.yearOfStudy.semester" , target = "semester")
    @Mapping( source = "course.students" , target = "studentProfileImage" , qualifiedByName = "getProfileImage")
    InstructorCourseDetailResponse toInstructorCourseDetailResponse(Instructor instructor , Course course);

    @Named("getProfileImage")
    default String getProfileImage(Student student) {
        return student.getUser().getProfileImage();
    }


    @Mapping(source = "user.uuid", target = "uuid")
    @Mapping(source = "user.nameEn", target = "nameEn")
    @Mapping(source = "user.nameKh", target = "nameKh")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.dob", target = "dob")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.profileImage", target = "profileImage")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.currentAddress", target = "currentAddress")
    @Mapping(source = "user.birthPlace", target = "birthPlace")
    @Mapping(source = "courses", target = "courses", qualifiedByName = "toCourseWithUsersResponseSet")
    InstructorCoursesResponse toInstructorCoursesResponse(Instructor instructor);


}
