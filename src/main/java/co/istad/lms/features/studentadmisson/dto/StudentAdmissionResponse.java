package co.istad.lms.features.studentadmisson.dto;

import java.time.LocalDate;

public record StudentAdmissionResponse(
        Long id,
        String uuid,
        String nameEn,
        String nameKh,
        String email,
        String highSchool,
        String phoneNumber,
        LocalDate dob,
        String pob,
        String bacIiGrade,
        String gender,
        String avatar,
        String address,
        String guardianContact,
        String guardianRelationShip,
        String knownIstad,
        String identity,
        String biography,
        String shiftAlias,
        String studyProgramAlias,
        String degreeAlias,
        Boolean isDeleted
) {
}
