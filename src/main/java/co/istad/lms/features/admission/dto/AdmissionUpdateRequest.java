package co.istad.lms.features.admission.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AdmissionUpdateRequest(
        @Size(max = 50, message = "Name (English) cannot be longer than 50 characters")
        String nameEn,

        @Size(max = 50, message = "Name (Khmer) cannot be longer than 50 characters")
        String nameKh,

        LocalDate dob,

        @Size(max = 20, message = "Gender cannot be longer than 20 characters")
        String gender,

        String avatar,

        String address,

        @Size(max = 50, message = "Family Phone Number cannot be longer than 50 characters")
        String familyPhoneNumber,

        String biography,

        String shift,

        String studyProgram,

        String degreeUuid
) {
}
