package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

import java.time.LocalDate;

public record StudentRequestUpdate(

        String nameEn,
        String nameKh,
        String username,
        String gender,
        LocalDate dob,
        String email,
        String password,
        String profileImage,
        String phoneNumber,
        String currentAddress,
        JsonBirthPlace birthPlace

) {
}
