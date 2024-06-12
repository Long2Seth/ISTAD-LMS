package co.istad.lms.features.student.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;
import java.util.Set;

public record StudentResponseDetail(
        String uuid,
        String nameEn,
        String nameKh,
        String username,
        String gender,
        LocalDate dob,
        String email,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        JsonBirthPlace birthPlace,

        Set<ClassResponse> classes,
        Set<CourseResponse> courses
) {
}
