package co.istad.lms.features.course.dto;

public record CourseStudentResponse(
        String uuid,
        String title,
        Integer credit,
        String logo,
        String description,

        // Instructor information for this course
        String instructorProfileImage,
        String instructorName,

        // YearOfStudy
        Integer year,
        Integer semester

) {
}
