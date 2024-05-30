package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.user.dto.UserRequestDetail;

import java.time.LocalDate;

public record InstructorRequestDetail(
        String highSchool,
        LocalDate highSchoolGraduationDate,
        String degree,
        LocalDate degreeGraduationDate,
        String major,
        String studyAtUniversityOrInstitution,
        String experienceAtWorkingPlace,
        Integer experienceYear,
        UserRequestDetail user

) {
}
