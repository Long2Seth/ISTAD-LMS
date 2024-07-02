package co.istad.lms.features.student.dto;

import co.istad.lms.features.academicyear.dto.AcademicYearResponse;
import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record StudentSemesterScoreResponse(
        String uuid,
        String cardId,
        String nameEn,
        String gender,
        LocalDate dob,
        Integer status,

        String classCode,

        AcademicYearResponse academicYear,

        Set<CourseSemesterScoreResponse> courses,

        String grade,
        Double total

) {
}
