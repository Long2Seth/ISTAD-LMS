package co.istad.lms.features.instructor.dto;


import co.istad.lms.features.course.dto.CourseWithUsersResponse;

import java.time.LocalDate;
import java.util.Set;

public record InstructorCoursesResponse(


        String uuid,


        String nameEn,


        String nameKh,


        String username,


        String gender,


        LocalDate dob,


        String email,


        String profileImage,


        String phoneNumber,


        String currentAddress,


        String birthPlace,


        Set<CourseWithUsersResponse> courses
) {}

