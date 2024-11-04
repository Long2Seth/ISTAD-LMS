package co.istad.lms.features.instructor.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Set;

public record InstructorCourseDetailResponse(


        // YearOfStudy information
        Integer year,
        Integer semester,

        //Instruction information


        String linkGit,


        String linkLinkedin,


        String linkTelegram,


        String uploadCv,


        String identityCard,


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
        Set<String> studentProfileImage,

        // Classes start
        LocalDate classesStart

) {
}
