package co.istad.lms.features.curriculum.dto;

import co.istad.lms.domain.json.SubjectOfSemester;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CurriculumRequest(

        @NotNull(message = "year is required")
        String year,

        @NotNull(message = "semester is required")
        String semester,
        @NotNull(message = "subjectsOfSemester is required")
        List<SubjectOfSemester> subjectsOfSemester
) {
}
