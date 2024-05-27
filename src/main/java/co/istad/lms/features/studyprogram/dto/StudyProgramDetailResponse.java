package co.istad.lms.features.studyprogram.dto;

import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;

import java.util.Set;

public record StudyProgramDetailResponse(

        String alias,
        String studyProgramName,
        String description,
        String logo,
        Boolean isDeleted,

        DegreeResponse degree,

        FacultyResponse faculty,

        Set<SubjectResponse> subjects

) {
}
