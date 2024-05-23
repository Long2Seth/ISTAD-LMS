package co.istad.lms.features.subject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

public record SubjectUpdateRequest(

        String alias,
        @Size(max = 50, message = "Subject name cannot be longer than 50 characters")
        String subjectName,

        String description,
        String subjectLogo,
        Integer credit,
        Integer duration


) {
}
