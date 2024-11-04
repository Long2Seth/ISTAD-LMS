package co.istad.lms.features.academicyear.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AcademicYearRequest(

        @NotBlank(message = "alias is require")
        String alias,

        @NotBlank(message = "academicYear is require")
        String academicYear
) {
}
