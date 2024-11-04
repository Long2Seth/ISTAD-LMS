package co.istad.lms.features.curriculum.dto;

import co.istad.lms.domain.json.SubjectOfSemester;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SubjectOfSemesterRequest(

        @NotNull(message = "year is required")
        List<SubjectOfSemester> subjectsOfSemester
) {
}
