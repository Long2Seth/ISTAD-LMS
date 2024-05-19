package co.istad.lms.features.admission.dto;

import org.springframework.cglib.core.Local;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AdmissionResponse(
        String uuid,
        String nameEn,
        String nameKh,
        String email,
        LocalDate dob,
        String gender,
        String avatar,
        String address,
        String familyPhoneNumber,
        String biography,
        String shift,
        String studyProgram,
        String degreeUuid,
        String createdBy,
        LocalDateTime createdAt,
        String lastModifiedBy,
        LocalDateTime lastModifiedAt,
        Boolean isDeleted
) {

}