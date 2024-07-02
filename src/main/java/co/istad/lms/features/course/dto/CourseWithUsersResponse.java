package co.istad.lms.features.course.dto;

public record CourseWithUsersResponse(



        String uuid,


        String title,



        Integer credit,



        String logo,



        Integer status,



        String description,





        // Instructor information for this course
        String instructorProfileImage,



        String instructorName,




        // YearOfStudy
        Integer year,



        Integer semester




) {
}
