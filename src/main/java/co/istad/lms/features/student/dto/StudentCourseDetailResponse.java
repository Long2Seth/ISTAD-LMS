package co.istad.lms.features.student.dto;

import java.time.LocalDate;
import java.util.Set;

public record StudentCourseDetailResponse(

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
        String position

        //Student profile
//        Set<String> studentProfileImage,

        // Classes start
//        LocalDate classesStart


) {
}
