package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;

public record StudentResponseDetail(
        String uuid,
        boolean status ,
        UserResponseDetail user
) {
}
