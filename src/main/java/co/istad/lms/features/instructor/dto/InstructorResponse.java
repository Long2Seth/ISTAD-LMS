package co.istad.lms.features.instructor.dto;


import co.istad.lms.features.user.dto.UserResponse;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record InstructorResponse(
        String uuid,
        boolean status,
        UserResponse users
) {
}
