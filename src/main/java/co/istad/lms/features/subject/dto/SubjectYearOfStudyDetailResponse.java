package co.istad.lms.features.subject.dto;

import co.istad.lms.features.studyprogram.dto.StudyProgramResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyDetailResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudyResponse;
import co.istad.lms.features.yearofstudy.dto.YearOfStudySubjectResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Set;

public record SubjectYearOfStudyDetailResponse(

        String alias,
        String title,
        String description,
        String logo,
        Integer practice,
        Integer internship,
        Integer theory,
        Integer duration,

        Integer credit,


        Boolean isDeleted,

        Boolean isDraft,

        YearOfStudySubjectResponse yearOfStudy
) {
}
