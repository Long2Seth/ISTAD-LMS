package co.istad.lms.features.instructor.dto;

import co.istad.lms.features.course.dto.CourseStudentResponse;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponseDetail;

import java.time.LocalDate;
import java.util.Set;

public record InstructorResponseDetail(


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



        String birthPlace ,



        String highSchool,



        String highSchoolGraduationDate,




        String degreeGraduationDate,



        String studyAtUniversityOrInstitution,



        String experienceAtWorkingPlace,



        Integer experienceYear,




        Set< String> educations,




        Set<String> skills ,



        Set<CourseStudentResponse> courses


) {
}
