package co.istad.lms.features.studyprogram.dto;

import co.istad.lms.domain.YearOfStudy;
import co.istad.lms.features.degree.dto.DegreeResponse;
import co.istad.lms.features.faculties.dto.FacultyResponse;
import co.istad.lms.features.subject.dto.SubjectResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;

import java.util.Set;

public record StudyProgramDetailResponse(

        String alias,
        String studyProgramName,
        String description,
        String logo,
        Boolean isDeleted,

        Boolean isDraft,

        DegreeResponse degree,

        FacultyResponse faculty


) {
}
