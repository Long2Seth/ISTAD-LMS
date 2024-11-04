package co.istad.lms.features.summarydashboard.dto;

import co.istad.lms.features.lecture.dto.LectureResponse;

import java.util.Set;

public record SummaryDashboardDetailResponse(

        Long totalStudent,
        Long totalDropStudent,
        Long totalActiveStudent,
        Long totalClassActive,
        Long totalCourseActive,
        Set<LectureResponse> currentLecture,
        
        Long newUser,

        Double totalStudentPayment

) {
}
