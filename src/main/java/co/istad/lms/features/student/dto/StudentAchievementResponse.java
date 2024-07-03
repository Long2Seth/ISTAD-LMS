package co.istad.lms.features.student.dto;

import co.istad.lms.features.yearofstudy.dto.YearOfStudyStudentAchievementResponse;
import java.time.LocalDate;
import java.util.Set;

public record StudentAchievementResponse(

        String profileImage,
        String nameEn,
        String nameKh,
        LocalDate dob,
        String degree,
        String major,
        String avatar,
        Set<YearOfStudyStudentAchievementResponse> yearOfStudiesStudents
) {
}
