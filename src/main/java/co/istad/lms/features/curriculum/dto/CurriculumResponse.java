package co.istad.lms.features.curriculum.dto;

import co.istad.lms.domain.json.SubjectOfSemester;

import java.util.List;

public record CurriculumResponse(
        String year,
        String semester,
        List<SubjectOfSemester> subjectsOfSemester
) {
}
