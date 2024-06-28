package co.istad.lms.features.student.dto;

import java.time.LocalDate;
import java.util.Set;

public record StudentCourseDetailResponse(

        Integer year,
        Integer semester,
        String courseTitle,
        String courseDescription,
        String courseLogo,
        Integer credit,
        Integer theory,
        Integer practice,
        Integer internship,

        // Instructor information
        String instructorName,
        String position,

        //Student profile
        Set<String> studentProfile,

        // Classes start
        LocalDate classesStart



        ) {
}
