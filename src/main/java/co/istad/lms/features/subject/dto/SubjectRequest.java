package co.istad.lms.features.subject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.util.Set;

public record SubjectRequest(

        @NotBlank(message = "alias is required")
        @Size(max = 100, message = "alias can not be longer than 100 characters")
        String alias,

        @NotBlank(message = "Subject title is required")
        @Size(max = 100, message = "Subject Name can not be longer than 100 characters")
        String title,

        String description,
        String logo,

        @NotNull(message = "theory is require")
        @PositiveOrZero(message = "theory must be positive or zero")
        Integer theory,

        @NotNull(message = "practice is require")
        @PositiveOrZero(message = "practice must be positive or zero")
        Integer practice,

        @NotNull(message = "internship is require")
        @PositiveOrZero(message = "internship must be positive or zero")
        Integer internship,

        @NotNull(message = "duration is require")
        @PositiveOrZero(message = "duration must be positive or zero")
        Integer duration,

        @JsonProperty("curriculum")
        JsonNode curriculum,

        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}

