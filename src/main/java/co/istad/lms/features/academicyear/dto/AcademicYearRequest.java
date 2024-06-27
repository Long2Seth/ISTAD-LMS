package co.istad.lms.features.academicyear.dto;

import jakarta.validation.constraints.NotBlank;

public record AcademicYearRequest(

        @NotBlank(message = "academicYear is require")
        String academicYear
) {
}
