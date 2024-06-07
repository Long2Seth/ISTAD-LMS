package co.istad.lms.features.student.dto;

import co.istad.lms.domain.Course;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.UserRequestDetail;

import java.util.Set;

public record StudentRequestDetail (
        UserRequestDetail user
){
}
