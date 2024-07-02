package co.istad.lms.features.course.dto;


import co.istad.lms.features.instructor.dto.InstructorInfoResponse;

public record CourseLectureResponse(

        String uuid,
        String title,

        InstructorInfoResponse instructor
) {
}
