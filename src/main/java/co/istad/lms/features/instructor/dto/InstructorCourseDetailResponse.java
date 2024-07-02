package co.istad.lms.features.instructor.dto;

import java.time.LocalDate;

public record InstructorCourseDetailResponse(


        // YearOfStudy information
        Integer year,
        Integer semester,

        // Subject information
        String courseTitle,
        String courseDescription,
        String courseLogo,

        // Subject information
        Integer credit,
        Integer theory,
        Integer practice,
        Integer internship,

        // Instructor information
        String instructorName,
        String userProfileImage,
        String position,

        //Student profile
//        Set<String> studentProfileImage,

        // Classes start
        LocalDate classesStart

) {
}
