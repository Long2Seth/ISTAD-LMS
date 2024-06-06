package co.istad.lms.features.student.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.user.dto.UserResponse;
import co.istad.lms.features.user.dto.UserResponseDetail;

import java.util.Set;

public record StudentResponseDetail(
        String uuid,
        boolean status ,
        UserResponseDetail user,

        Set<ClassResponse> classes
) {
}
