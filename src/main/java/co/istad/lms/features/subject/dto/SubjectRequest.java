package co.istad.lms.features.subject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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
        @Positive(message = "theory must be positive")
        Integer theory,

        @NotNull(message = "practice is require")
        @Positive(message = "practice must be positive")
        Integer practice,

        @NotNull(message = "internship is require")
        @Positive(message = "internship must be positive")
        Integer internship,

        @NotNull(message = "duration is require")
        @Positive(message = "duration must be positive")
        Integer duration,

        @JsonProperty("curriculum")
        JsonNode curriculum,

        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}

