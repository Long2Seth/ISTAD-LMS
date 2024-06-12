package co.istad.lms.features.student.dto;

import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;
import java.util.Set;

public record StudentResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String email,
        String phoneNumber,
        LocalDate dob,
        String gender,
        String profileImage
) {
}
