package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.user.dto.UserResponseDetail;

public record InstructorResponseDetail(
        String highSchool,
        String highSchoolGraduationDate,
        String degree,
        String degreeGraduationDate,
        String major,
        String studyAtUniversityOrInstitution,
        String experienceAtWorkingPlace,
        String experienceYear,
        boolean status,
        UserResponseDetail user
) {
}
