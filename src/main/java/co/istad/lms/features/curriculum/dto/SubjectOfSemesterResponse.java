package co.istad.lms.features.curriculum.dto;

import co.istad.lms.domain.json.SubjectOfSemester;

import java.util.List;

public record SubjectOfSemesterResponse(
        List<SubjectOfSemester> subjectsOfSemester
) {
}
