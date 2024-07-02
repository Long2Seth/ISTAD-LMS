package co.istad.lms.features.student.dto;

import co.istad.lms.features.classes.dto.ClassResponse;
import co.istad.lms.features.course.dto.CourseResponse;
import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserResponse;

import java.time.LocalDate;
import java.util.Set;

public record StudentResponseDetail(
        String uuid,


        String cardId,



        Integer studentStatus,




        String profileImage,



        String nameEn,


        String nameKh,



        String gender,



        String email,




        String username,



        String phoneNumber,



        String guardianRelationShip,




        String familyPhoneNumber,




        String birthPlace,




        String currentAddress,




        String biography,



        String dob,



        String bacIiGrade,



        String highSchoolCertificate,


        String vocationTrainingCertificate,


        String anyValuableCertificate,


        Set<ClassResponse> classes,



        Set<CourseResponse> courses


) {
}
