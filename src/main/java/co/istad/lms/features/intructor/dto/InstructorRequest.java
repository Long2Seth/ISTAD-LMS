package co.istad.lms.features.intructor.dto;


import co.istad.lms.features.user.dto.UserRequest;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record InstructorRequest(
        String highSchool,

        LocalDate highSchoolGraduationDate,

        String degree, // Bachelor, Master, Doctor

        LocalDate degreeGraduationDate,

        String major,

        String studyAtUniversityOrInstitution,

        String experienceAtWorkingPlace,

        LocalDate experienceYear, // experience compare per year

        boolean status,

        boolean isDeleted,

        UserRequest userRequest

) {
}
