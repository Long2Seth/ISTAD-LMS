package co.istad.lms.features.student.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;

import java.time.LocalDate;

public record StudentRequestUpdate(

        String gender,
        String profileImage,
        String phoneNumber,
        String familyPhoneNumber,
        String currentAddress,
        String birthPlace,
        String bio

) {
}
