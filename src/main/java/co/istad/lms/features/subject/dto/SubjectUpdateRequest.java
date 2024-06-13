package co.istad.lms.features.subject.dto;


import co.istad.lms.domain.StudyProgram;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SubjectUpdateRequest(

        @Size(max = 100, message = "alias is can not be longer than 100 characters")
        String alias,
        @Size(max = 100, message = "Subject title can not be longer than 100 characters")
        String title,

        String description,
        String logo,

        @Positive(message = "duration must be positive")
        Integer duration,

        @Positive(message = "theory must be positive")
        Integer theory,

        @Positive(message = "practice must be positive")
        Integer practice,

        @Positive(message = "internship must be positive")
        Integer internship


) {
}
