package co.istad.lms.features.yearofstudy.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record YearOfStudySubjectRequest(

        @NotNull(message = "aliasOfSubjects can not be null")
        Set<String> aliasOfSubjects
) {
}
