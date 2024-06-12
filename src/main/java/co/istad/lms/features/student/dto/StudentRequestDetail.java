package co.istad.lms.features.student.dto;

import co.istad.lms.domain.Course;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequestDetail;

import java.time.LocalDate;
import java.util.Set;

public record StudentRequestDetail (
        String nameEn,
        String nameKh,
        String username,
        String gender,
        LocalDate dob,
        String email,
        String password,
        String profileImage,
        String phoneNumber,
        String cityOrProvince,
        String khanOrDistrict,
        String sangkatOrCommune,
        String street,
        JsonBirthPlace birthPlace
){
}
