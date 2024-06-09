package co.istad.lms.features.subject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SubjectRequest(

        @NotBlank(message = "Alias is required")
        @Size(max = 50, message = "Alias cannot be longer than 50 characters")
        String alias,

        @NotBlank(message = "Subject title is required")
        @Size(max = 50, message = "Subject Name cannot be longer than 50 characters")
        String title,

        String description,
        String logo,

        @NotNull(message = "theory is require")
        Integer theory,

        @NotNull(message = "practice is require")
        Integer practice,
        @NotNull(message = "internship is require")
        Integer internship,

        @NotNull(message = "duration is require")
        Integer duration,

        @JsonProperty("curriculum")
        JsonNode curriculum,

        @NotNull(message = "isDraft is required")
        Boolean isDraft

) {
}

