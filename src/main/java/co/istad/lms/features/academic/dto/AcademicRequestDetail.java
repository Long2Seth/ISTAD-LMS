package co.istad.lms.features.academic.dto;

import co.istad.lms.features.user.dto.UserRequest;
import co.istad.lms.features.user.dto.UserRequestDetail;
import co.istad.lms.features.user.dto.UserResponse;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AcademicRequestDetail
        (
                String uuid,
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
