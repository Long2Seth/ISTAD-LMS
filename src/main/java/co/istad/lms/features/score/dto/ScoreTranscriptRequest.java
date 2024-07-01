package co.istad.lms.features.score.dto;

import co.istad.lms.features.course.dto.CourseSemesterScoreResponse;
import co.istad.lms.features.student.dto.StudentTranscriptResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record ScoreTranscriptRequest(
        @NotBlank(message = "studyProgramAlias is require")
        String studyProgramAlias,

        @NotBlank(message = "generationUuid is require")
        String generationAlias,

        @NotNull(message = "year is require")
        @Positive(message = "year is positive")
        @Max(value = 4, message = "max of year is 4")
        Integer year
) {
}
