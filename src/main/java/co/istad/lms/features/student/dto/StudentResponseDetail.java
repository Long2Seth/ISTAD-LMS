package co.istad.lms.features.student.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.UserResponse;

import java.util.Set;

public record StudentResponseDetail(
        String uuid,
        boolean status,
        UserResponse user,

        Set<ClassResponse> classes,
        Set<CourseResponse> courses
) {
}
