package co.istad.lms.features.course.dto;

import co.istad.lms.features.instructor.dto.InstructorCourseResponse;

public record CourseLectureResponse(

        String uuid,
        String title,

        InstructorCourseResponse instructor
) {
}
