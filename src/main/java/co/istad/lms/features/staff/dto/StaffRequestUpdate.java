package co.istad.lms.features.staff.dto;

import co.istad.lms.features.user.dto.JsonBirthPlace;
import co.istad.lms.features.user.dto.UserRequestDetail;

import java.time.LocalDate;

public record StaffRequestUpdate(



        String position,

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

        JsonBirthPlace birthPlace



) {
}
